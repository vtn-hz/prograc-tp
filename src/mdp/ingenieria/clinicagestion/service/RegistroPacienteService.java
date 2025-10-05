package mdp.ingenieria.clinicagestion.service;

import java.util.HashMap;

import mdp.ingenieria.clinicagestion.exception.*;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.model.persona.paciente.registro.RegistroPaciente;

public class RegistroPacienteService {
	
	private HashMap<Integer, RegistroPaciente> pacientes;
	
	private GestorAtencionPacienteService gestorAtencionService;
	
	private RegistroMedicoService registroMedicoService;
	
	public RegistroPacienteService( 
			GestorAtencionPacienteService gestorAtencionService,
			RegistroMedicoService registroMedicoService
	) 
	{
		this.registroMedicoService = registroMedicoService;
		this.gestorAtencionService = gestorAtencionService;
		this.pacientes = new HashMap<>();
	}
	
	public RegistroPaciente buscarPaciente ( Paciente paciente ) throws PacienteNoRegistradoException 
	{
		RegistroPaciente  registroPaciente = this.pacientes.get( paciente.getNroHistoriaClinica() );
		if (registroPaciente == null) {
			throw new PacienteNoRegistradoException( paciente );
		}
		
		return registroPaciente;
	}
	
	public boolean isRegistrado( Paciente paciente )  
	{
		return this.pacientes.containsKey( paciente.getNroHistoriaClinica() );
	}
	
	public void registrarPaciente ( Paciente paciente ) throws PacienteNroHistoriaClinicaDuplicadoException
	{
		if (this.isRegistrado(paciente)) {
			throw new PacienteNroHistoriaClinicaDuplicadoException( paciente );
		}
		
		this.pacientes.put( paciente.getNroHistoriaClinica(), new RegistroPaciente(paciente));	
	}
	
	public void ingresaPaciente( Paciente paciente ) throws PacienteNoRegistradoException
	{
		this.buscarPaciente(paciente).addRegistroIngreso(); // asume cliclo de vida 
		/**
		 * anuncio -> atiendo -> salgo
		 * si spameas que te anuncias te quedan todos incompletas
		 * agregamos control para que no te deje crear hasta que no termines
		 * la que estas haciendo?
		 */
		this.gestorAtencionService.anunciar( paciente );
	}
	
	public void atiendePaciente( IMedico medico , Paciente paciente ) throws PacienteException, MedicoNoRegistradoException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
	
		if (!this.registroMedicoService.isRegistrado(medico)) {
			throw new MedicoNoRegistradoException( medico.getNumeroMatricula() );
		}
		
		this.gestorAtencionService.atender( registroPaciente.getPaciente() );
		registroPaciente.addAtendidoPor(medico);
	}	
	
	public void internaPaciente( Paciente paciente, Habitacion habitacion ) throws PacienteException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
		
		if (!this.gestorAtencionService.isAtendido(paciente))
			throw new PacienteNoAtendidoException( paciente );
		
		registroPaciente.setHabitacion(habitacion);
	}
	
	public Factura egresaPaciente( Paciente paciente, int dias ) throws PacienteException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
		Factura factura;
		
		try {
			this.gestorAtencionService.egresar(paciente);
			registroPaciente.setDias(dias);
			
			
			registroPaciente.finalizarIngreso();	
			factura = registroPaciente.getFactura();
			
			this.registroMedicoService.actualizarConsultasMedicos(
					registroPaciente.getAtendidoPor(), paciente.getNyA(),
					factura.getFechaEgreso()
			);
		}catch(PacienteNoAtendidoException e) {
			factura = null;
		} catch (MedicoNoRegistradoException e) {
			factura = null;
			// nunca va a suceder, ya que los medicos que se 
			// agregan a registroPaciente se verifican antes que existan
		}
		
		return factura;
	}
	
	
	public Factura egresaPaciente( Paciente paciente ) throws PacienteException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
		Factura factura;
		
		try {
			this.gestorAtencionService.egresar(paciente);
			registroPaciente.finalizarIngreso();
			factura = registroPaciente.getFactura();
			
			this.registroMedicoService.actualizarConsultasMedicos(
					registroPaciente.getAtendidoPor(), paciente.getNyA(),
					factura.getFechaEgreso()
			);
		}catch(PacienteNoAtendidoException e) {
			factura = null;
		} catch (MedicoNoRegistradoException e) {
			factura = null;
			// nunca va a suceder, ya que los medicos que se 
			// agregan a registroPaciente se verifican antes que existan
		}
		
		return factura;
	}
	
}
