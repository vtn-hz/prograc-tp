package mdp.ingenieria.clinicagestion;

import java.time.LocalDateTime;
import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.exception.MedicoMatriculaDuplicadaException;
import mdp.ingenieria.clinicagestion.exception.MedicoNoRegistradoException;
import mdp.ingenieria.clinicagestion.exception.PacienteException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoRegistradoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNroHistoriaClinicaDuplicadoException;

import mdp.ingenieria.clinicagestion.model.Sistema;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.PacienteFactory;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.MedicoConsulta;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.view.Printer;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.MedicoFactory;
import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.clinica.HabitacionFactory;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;

public class Main
{

    public static void main(String[] args) 
    {
        Sistema sistema = Sistema.getInstance();   
        sistema.initializeClinica("Clínica Maguvisi", "0223 481-6600", "Mar del plata", "Av. Juan B. Justo 4302");
        
        Printer.printClinica( sistema.getClinica() );
        
        PacienteFactory pacienteFactory = new PacienteFactory();
        MedicoFactory medicoFactory = new MedicoFactory();
        HabitacionFactory habitacionFactory = new HabitacionFactory();
        
        Paciente pacienteNino = pacienteFactory.create("Juan Pérez", "45123456", "223-4567890", "Mar del Plata", "Av. Independencia 1234", 1001, PacienteFactory.PACIENTE_NINO);
        Paciente pacienteJoven = pacienteFactory.create("María González", "38567890", "223-5678901", "Mar del Plata", "Calle Mitre 5678", 1002, PacienteFactory.PACIENTE_JOVEN);
        Paciente pacienteMayor = pacienteFactory.create("Roberto Fernández", "12345678", "223-6789012", "Mar del Plata", "Av. Colón 9101", 1003, PacienteFactory.PACIENTE_MAYOR);
        
        IMedico medicoClinicoSinDecorador = medicoFactory.create(12345, "Dr. Carlos López", "20123456", "223-1111111", "Mar del Plata", "Calle San Martín 100", MedicoFactory.MEDICO_CLINICO);
        IMedico medicoCirujanoPermanente = medicoFactory.create(23456, "Dra. Ana Martínez", "27234567", "223-2222222", "Mar del Plata", "Av. Luro 200", MedicoFactory.MEDICO_CIRUJANO, MedicoFactory.CONTRATACION_PERMANENTE);
        IMedico medicoPediatraResidente = medicoFactory.create(34567, "Dr. Luis Rodríguez", "30345678", "223-3333333", "Mar del Plata", "Calle Córdoba 300", MedicoFactory.MEDICO_PEDIATRA, MedicoFactory.CONTRATACION_RESIDENTE);
        IMedico medicoClinicoMagisterPermanente = medicoFactory.create(45678, "Dra. Laura Sánchez", "33456789", "223-4444444", "Mar del Plata", "Av. Constitución 400", MedicoFactory.MEDICO_CLINICO, MedicoFactory.CONTRATACION_PERMANENTE, MedicoFactory.POSTGRADO_MAGISTER);
        IMedico medicoCirujanoDoctoradoResidente = medicoFactory.create(56789, "Dr. Miguel Torres", "35567890", "223-5555555", "Mar del Plata", "Calle Belgrano 500", MedicoFactory.MEDICO_CIRUJANO, MedicoFactory.CONTRATACION_RESIDENTE, MedicoFactory.POSTGRADO_DOCTORADO);
        
        Habitacion habitacionPrivada    = habitacionFactory.create(HabitacionFactory.HABITACION_PRIVADA, 5000.0, 500.0);
        Habitacion habitacionCompartida = habitacionFactory.create(HabitacionFactory.HABITACION_COMPARTIDA, 3000.0, 300.0);
        Habitacion habitacionTerapia    = habitacionFactory.create(HabitacionFactory.HABITACION_TERAPIA_INTENSIVA, 7000.0, 3.0);

		try {
			sistema.registraMedico(medicoClinicoSinDecorador)
				   .registraMedico(medicoCirujanoPermanente)
				   .registraMedico(medicoCirujanoDoctoradoResidente);
			
			sistema.registraPaciente(pacienteNino)
			   .registraPaciente(pacienteJoven)
			   .registraPaciente(pacienteMayor);
			
		} catch (MedicoMatriculaDuplicadaException | PacienteNroHistoriaClinicaDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			sistema.ingresaPaciente(pacienteNino)
				   .ingresaPaciente(pacienteJoven);
			
			Printer.printSalas( SalaEsperaPrivada.getInstance() , SalaEsperaPatio.getInstance());
			sistema.ingresaPaciente(pacienteMayor);
			System.out.println("Mayor vs Nino");
			Printer.printSalas( SalaEsperaPrivada.getInstance() , SalaEsperaPatio.getInstance());
			
		} catch (PacienteNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        try {
        	Paciente paciente = sistema.siguientePaciente();
			sistema.atiendePaciente(medicoClinicoSinDecorador, paciente);
			sistema.atiendePaciente(medicoCirujanoPermanente, paciente);
			sistema.internaPaciente(pacienteNino, habitacionPrivada);
			
			Factura facturaNino = sistema.egresaPaciente(pacienteNino, 5);
			Printer.printFactura(facturaNino);
		} catch (PacienteException | MedicoNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 
        try {
        	Paciente paciente = sistema.siguientePaciente();
			sistema.atiendePaciente(medicoCirujanoDoctoradoResidente, paciente);
			sistema.atiendePaciente(medicoCirujanoPermanente, paciente);
			sistema.internaPaciente(pacienteJoven, habitacionTerapia);
			
			Factura facturaJoven = sistema.egresaPaciente(pacienteJoven, 10);
			Printer.printFactura(facturaJoven);
		} catch (PacienteException | MedicoNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
		try {
			ArrayList<MedicoConsulta> consultasMedicas;
			consultasMedicas = sistema.reporteMedico(
					medicoCirujanoPermanente, 
					LocalDateTime.now(), 
					LocalDateTime.now().plusDays(15)
			);
		    Printer.printReporte(medicoCirujanoPermanente, consultasMedicas, LocalDateTime.now(), LocalDateTime.now().plusDays(15));    
		} catch (MedicoNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        

       
        
        // exception cases
        try {
			sistema.atiendePaciente(medicoCirujanoPermanente, pacienteNino);
		} catch (PacienteException | MedicoNoRegistradoException e) {
			System.out.println( " - " +  e.getMessage() );
		}
        
        try {
			sistema.atiendePaciente(medicoPediatraResidente, pacienteJoven);
		} catch (PacienteException | MedicoNoRegistradoException e) {
			System.out.println( " - " +  e.getMessage() );
		}
     
        try {
			sistema.egresaPaciente(pacienteNino, 4);
		} catch (PacienteException e) {
			System.out.println( " - " +  e.getMessage() );
		}
        // exception cases
        
        
    }
}