package mdp.ingenieria.clinicagestion.service;

import java.util.HashMap;

import mdp.ingenieria.clinicagestion.exception.*;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.model.persona.paciente.registro.RegistroPaciente;

/**
 * Servicio que administra el registro y ciclo de vida de los pacientes:
 * alta, ingreso, atención, internación y egreso con generación de factura.
 */
public class RegistroPacienteService {
	
	private HashMap<Integer, RegistroPaciente> pacientes;
	private GestorAtencionPacienteService gestorAtencionService;
	private RegistroMedicoService registroMedicoService;

    /**
     * Constructor del servicio.
     *
     * <b>pre:</b> gestorAtencionService y registroMedicoService no deben ser null <br>
     * <b>post:</b> se inicializa el índice de pacientes vacío
     *
     * @param gestorAtencionService         gestor de anuncios, atención y egresos de sala de espera
     * @param registroMedicoService         servicio para validar y actualizar médicos
     */
	public RegistroPacienteService( 
			GestorAtencionPacienteService gestorAtencionService,
			RegistroMedicoService registroMedicoService
	) 
	{
		this.registroMedicoService = registroMedicoService;
		this.gestorAtencionService = gestorAtencionService;
		this.pacientes = new HashMap<>();
	}

    /**
     * Busca y retorna el RegistroPaciente asociado al paciente.
     *
     * <b>pre:</b> paciente no es null <br>
     * <b>post:</b> devuelve el registro correspondiente
     *
     * @param paciente paciente a buscar
     * @return registro del paciente
     * @throws PacienteNoRegistradoException si el paciente no está registrado
     */
	public RegistroPaciente buscarPaciente ( Paciente paciente ) throws PacienteNoRegistradoException 
	{
		RegistroPaciente  registroPaciente = this.pacientes.get( paciente.getNroHistoriaClinica() );
		if (registroPaciente == null) {
			throw new PacienteNoRegistradoException( paciente );
		}
		
		return registroPaciente;
	}

    /**
     * Indica si el paciente ya está registrado.
     * <b>post:</b> retorna true si existe un registro para su historia clínica
     *
     * @param paciente paciente a consultar
     * @return true si está registrado; de lo contrario false
     */
	public boolean isRegistrado( Paciente paciente )  
	{
		return this.pacientes.containsKey( paciente.getNroHistoriaClinica() );
	}

    /**
     * Registra un nuevo paciente en el sistema.
     *
     * <b>pre:</b> paciente no es null <br>
     * <b>post:</b> se crea un RegistroPaciente
     *
     * @param paciente paciente a registrar
     * @throws PacienteNroHistoriaClinicaDuplicadoException si ya existe un paciente con esa historia clínica
     */
	public void registrarPaciente ( Paciente paciente ) throws PacienteNroHistoriaClinicaDuplicadoException
	{
		if (this.isRegistrado(paciente)) {
			throw new PacienteNroHistoriaClinicaDuplicadoException( paciente );
		}
		
		this.pacientes.put( paciente.getNroHistoriaClinica(), new RegistroPaciente(paciente));	
	}

    /**
     * Inicia un ingreso para el paciente y lo anuncia en la sala de espera.
     * <b>pre:</b> paciente != null <br>
     * <b>post:</b> se agrega un nuevo ingreso al RegistroPaciente y se anuncia en la sala de espera
     *
     * @param paciente paciente que ingresa
     * @throws PacienteNoRegistradoException si el paciente no está registrado
     */
	public void ingresaPaciente( Paciente paciente ) throws PacienteNoRegistradoException
	{
		this.buscarPaciente(paciente).addRegistroIngreso();
		this.gestorAtencionService.anunciar( paciente );
	}

    /**
     * Registra que un médico atiende al paciente.
     *
     * <b>pre:</b> paciente != null ; medico != null <br>
     * <b>post:</b> el paciente pasa a atención (sale de la sala) y se agrega el médico al ingreso
     *
     * @param medico        médico que atiende
     * @param paciente      paciente atendido
     * @throws PacienteException si el paciente no tiene ingreso activo
     * @throws MedicoNoRegistradoException si el médico no está registrado
     */
	public void atiendePaciente( IMedico medico , Paciente paciente ) throws PacienteException, MedicoNoRegistradoException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
	
		if (!this.registroMedicoService.isRegistrado(medico)) {
			throw new MedicoNoRegistradoException( medico.getNumeroMatricula() );
		}
		
		this.gestorAtencionService.atender( registroPaciente.getPaciente() );
		registroPaciente.addAtendidoPor(medico);
	}

    /**
     * Asigna una habitación al paciente.
     *
     * <b>pre:</b> el paciente está registrado y con ingreso activo; el paciente ya ha sido atendido <br>
     * <b>post:</b> la Habitacion queda asociada al ingreso actual
     *
     * @param paciente          paciente a internar
     * @param habitacion        habitación asignada
     * @throws PacienteException si el paciente no fue atendido o no tiene ingreso activo
     */
	public void internaPaciente( Paciente paciente, Habitacion habitacion ) throws PacienteException
	{
		RegistroPaciente registroPaciente = this.buscarPaciente(paciente);
		
		if (!this.gestorAtencionService.isAtendido(paciente))
			throw new PacienteNoAtendidoException( paciente );
		
		registroPaciente.setHabitacion(habitacion);
	}

    /**
     * Egresar al paciente estableciendo la cantidad de días y generando su Factura.
     *
     * <b>pre:</b> el paciente está registrado y tiene un ingreso activo; dias ≥ 0 <br>
     * <b>post:</b> se finaliza el ingreso, se genera la factura y se actualizan las consultas de los médicos
     *
     * @param paciente      paciente a egresar
     * @param dias          cantidad de días a facturar
     * @return Factura generada o null si el paciente no fue atendido
     * @throws PacienteException si el paciente no está registrado o no tiene ingreso activo
     */
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
		} catch(PacienteNoAtendidoException e) {
			factura = null;
		} catch (MedicoNoRegistradoException e) {
			factura = null;
			/* 
				nunca va a suceder, ya que los medicos que se 
				agregan a registroPaciente se verifican antes que existan
			*/
		}
		
		return factura;
	}
	
}
