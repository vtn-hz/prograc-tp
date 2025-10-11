package mdp.ingenieria.clinicagestion.facade;

import mdp.ingenieria.clinicagestion.exception.*;
import mdp.ingenieria.clinicagestion.model.clinica.Clinica;
import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.MedicoConsulta;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.service.GestorAtencionPacienteService;
import mdp.ingenieria.clinicagestion.service.RegistroMedicoService;
import mdp.ingenieria.clinicagestion.service.RegistroPacienteService;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Fachada del sistema patrones Singleton y Facade que coordinan los servicios de registro y atención de pacientes y médicos.
 */
public class Sistema {
	
    private static Sistema _instancia;
    
    private GestorAtencionPacienteService gestorAtencionPacienteService;
    
    private RegistroMedicoService registroMedicoService;
    
    private RegistroPacienteService registroPacienteService;

    /**
     * Contructor privado del sistema
     * <b>post:</b> se crean los servicios de atención, médicos y pacientes
     */
    private Sistema() {
        this.gestorAtencionPacienteService 	= new GestorAtencionPacienteService();
        this.registroMedicoService			= new RegistroMedicoService();
        this.registroPacienteService		= new RegistroPacienteService(this.gestorAtencionPacienteService, this.registroMedicoService);
    }

    /**
     * Retorna la instancia única del sistema.
     * <b>post:</b> existe solo una instancia de Sistema
     *
     * @return instancia singleton de {@link Sistema}
     */
    public static Sistema getInstance() {
        if (_instancia == null) {
            _instancia = new Sistema();
        }
        return _instancia;
    }
   
    /**
     * <b>post: </b> inicia la clinica 
     * 
     * @param nombre
     * @param telefono
     * @param ciudad
     * @param direccion
     * @return Sistema
     */
    public Sistema initializeClinica(String nombre, String telefono, String ciudad, String direccion) 
    {
    	Clinica.initialize(nombre, telefono, ciudad, direccion);
    	return this;
    }
    
    /**
     * @return Clinica
     */
    public Clinica getClinica() 
    {
    	return Clinica.getInstance();
    }


    /**
     * Registra un médico en el sistema.
     *
     * <b>pre:</b> medico no debe ser null <br>
     * <b>post:</b> el médico queda registrado en RegistroMedicoService
     *
     * @param medico profesional a registrar
     * @return esta misma instancia para encadenar llamadas
     * @throws MedicoMatriculaDuplicadaException si ya existe un médico con la misma matrícula
     */
    public Sistema registraMedico(IMedico medico) throws MedicoMatriculaDuplicadaException {
        this.registroMedicoService.registraMedico(medico);
        return this;
    }

    /**
     * Registra un paciente en el sistema.
     *
     * <b>pre:</b> paciente no debe ser null <br>
     * <b>post:</b> el paciente queda registrado en RegistroPacienteService
     *
     * @param paciente paciente a registrar
     * @return esta misma instancia para encadenar llamadas
     * @throws PacienteNroHistoriaClinicaDuplicadoException si ya existe un paciente con el mismo número de historia clínica
     */
    public Sistema registraPaciente(Paciente paciente) throws PacienteNroHistoriaClinicaDuplicadoException {
        this.registroPacienteService.registrarPaciente(paciente);
        return this;
    }
    
    
    /**
     * Devuelve el siguiente paciente | null
     * 
     * @return Paciente|null
     */
    public Paciente siguientePaciente() {
    	return this.gestorAtencionPacienteService.getSigPacienteAtender();
    }

    /**
     * Inicia el ingreso de un paciente.
     *
     * <b>pre:</b> paciente no debe ser null <br>
     * <b>post:</b> el paciente queda con un ingreso activo
     *
     * @param paciente paciente a ingresar
     * @return esta misma instancia para encadenar llamadas
     * @throws PacienteNoRegistradoException si el paciente no está registrado
     */
    public Sistema ingresaPaciente(Paciente paciente) throws PacienteNoRegistradoException {
        this.registroPacienteService.ingresaPaciente(paciente);
        return this;
    }

    /**
     * Registra que un médico atiende a un paciente.
     *
     * <b>pre:</b> medico != null ; paciente != null <br>
     * <b>post:</b> se agrega la atención al registro del ingreso del paciente
     *
     * @param medico        médico que atiende
     * @param paciente      paciente atendido
     * @return esta misma instancia para encadenar llamadas
     * @throws PacienteException si el paciente no tiene ingreso activo u otra condición de dominio
     * @throws MedicoNoRegistradoException si el médico no está registrado
     */
    public Sistema atiendePaciente(IMedico medico, Paciente paciente) throws PacienteException, MedicoNoRegistradoException {
        this.registroPacienteService.atiendePaciente(medico, paciente);
        return this;
    }

    /**
     * Interna a un paciente en una habitación determinada.
     *
     * <b>pre:</b> paciente tiene ingreso activo; habitacion no es null <br>
     * <b>post:</b> la habitación queda asociada al ingreso del paciente
     *
     * @param paciente          paciente a internar
     * @param habitacion        habitación asignada
     * @return esta misma instancia para encadenar llamadas
     * @throws PacienteException si no hay ingreso activo
     */
    public Sistema internaPaciente(Paciente paciente, Habitacion habitacion) throws PacienteException {
        this.registroPacienteService.internaPaciente(paciente, habitacion);
        return this;
    }

    /**
     * Egreso de un paciente indicando la cantidad de días, generando su factura.
     *
     * <b>pre:</b> paciente != null ; dias ≥ 0 <br>
     * <b>post:</b> se cierra el ingreso y (se retorna la factura correspondiente | null si estaba en sala de espera)
     *
     * @param paciente      paciente a egresar
     * @param dias          cantidad de días a facturar
     * @return Factura generada
     * @throws PacienteException si no hay ingreso activo
     */
    public Factura egresaPaciente(Paciente paciente, int dias) throws PacienteException {
        return this.registroPacienteService.egresaPaciente(paciente, dias);
    }
    
    /**
     * Egreso de un paciente indicando la cantidad de días, generando su factura.
     *
     * <b>pre:</b> paciente != null <br>
     * <b>post:</b> se cierra el ingreso y (se retorna la factura correspondiente | null si estaba en sala de espera)
     *
     * @param paciente
     * @return
     * @throws PacienteException si no hay ingreso activo
     */
    public Factura egresaPaciente(Paciente paciente) throws PacienteException {
        return this.registroPacienteService.egresaPaciente(paciente);
    }

    /**
     * Devuelve reporte medico en una fecha especifica
     * 
     * <b>pre:</b> medico != null ; fechaInicio != null ; fechaFin != null<br>
     * 
     * @param medico
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws MedicoNoRegistradoException
     */
    public ArrayList<MedicoConsulta> reporteMedico(IMedico medico, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws MedicoNoRegistradoException {
        return registroMedicoService.buscarMedico(medico).getConsultasEfectuadasByFecha(fechaInicio, fechaFin);
    }

    /**
     * Devuelve reporte medico en una fecha especifica en String
     * 
     * <b>pre:</b> medico != null ; fechaInicio != null ; fechaFin != null<br>
     * 
     * @param medico
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws MedicoNoRegistradoException
     */
    public String reporteMedicoString(IMedico medico, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws MedicoNoRegistradoException {
        ArrayList<MedicoConsulta> reporte = reporteMedico(medico, fechaInicio, fechaFin);

        StringBuilder s = new StringBuilder();
        for (MedicoConsulta medicoConsulta : reporte) {
            s.append(medicoConsulta).append("\n");
        }
        return s.toString();
    }
}
