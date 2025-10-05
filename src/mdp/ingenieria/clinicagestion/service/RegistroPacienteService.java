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
	
	public RegistroPacienteService( GestorAtencionPacienteService gestorAtencionService ) 
	{
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
	
	public void atiendePaciente( IMedico medico , Paciente paciente ) throws PacienteException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
		// service de simon isRegistrado( medico )
		// por el momento asume que esta registrado
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
		}catch(PacienteNoAtendidoException e) {
			factura = null;
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
		}catch(PacienteNoAtendidoException e) {
			factura = null;
		}
		
		return factura;
	}
	
}
