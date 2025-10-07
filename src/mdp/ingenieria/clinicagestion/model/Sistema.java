package mdp.ingenieria.clinicagestion.model;

import mdp.ingenieria.clinicagestion.exception.*;
import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.service.GestorAtencionPacienteService;
import mdp.ingenieria.clinicagestion.service.RegistroMedicoService;
import mdp.ingenieria.clinicagestion.service.RegistroPacienteService;

import java.time.LocalDateTime;
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
        gestorAtencionPacienteService = new GestorAtencionPacienteService();
        registroMedicoService = new RegistroMedicoService();
        registroPacienteService = new RegistroPacienteService(gestorAtencionPacienteService, registroMedicoService);
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
     * Registra un médico en el sistema.
     *
     * <b>pre:</b> medico no debe ser null; su matrícula no debe existir previamente <br>
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
     * <b>pre:</b> paciente no debe ser null; su nro. de historia clínica no debe existir previamente <br>
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
     * Inicia el ingreso de un paciente.
     *
     * <b>pre:</b> paciente no debe ser null y debe estar previamente registrado <br>
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
     * <b>pre:</b> medico y paciente existen; el paciente debe tener un ingreso activo <br>
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
     * <b>pre:</b> paciente tiene ingreso activo; dias ≥ 0 <br>
     * <b>post:</b> se cierra el ingreso y se retorna la factura correspondiente
     *
     * @param paciente      paciente a egresar
     * @param dias          cantidad de días a facturar
     * @return Factura generada
     * @throws PacienteException si no hay ingreso activo
     */
    public Factura egresaPaciente(Paciente paciente, int dias) throws PacienteException {
        return this.registroPacienteService.egresaPaciente(paciente, dias);
    }
    public Factura egresaPaciente(Paciente paciente) throws PacienteException {
        return this.registroPacienteService.egresaPaciente(paciente);
    }

    public void reporteMedico(IMedico medico, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // to-do
    }
}
