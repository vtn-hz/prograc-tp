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

public class Sistema {
    private static Sistema _instancia;
    private GestorAtencionPacienteService gestorAtencionPacienteService;
    private RegistroMedicoService registroMedicoService;
    private RegistroPacienteService registroPacienteService;

    private Sistema() {
        gestorAtencionPacienteService = new GestorAtencionPacienteService();
        registroMedicoService = new RegistroMedicoService();
        registroPacienteService = new RegistroPacienteService(gestorAtencionPacienteService, registroMedicoService);
    }

    public static Sistema getInstance() {
        if (_instancia == null) {
            _instancia = new Sistema();
        }
        return _instancia;
    }

    public Sistema registraMedico(IMedico medico) throws MedicoMatriculaDuplicadaException {
        this.registroMedicoService.registraMedico(medico);
        return this;
    }

    public Sistema registraPaciente(Paciente paciente) throws PacienteNroHistoriaClinicaDuplicadoException {
        this.registroPacienteService.registrarPaciente(paciente);
        return this;
    }

    public Sistema ingresaPaciente(Paciente paciente) throws PacienteNoRegistradoException {
        this.registroPacienteService.ingresaPaciente(paciente);
        return this;
    }

    public Sistema atiendePaciente(IMedico medico, Paciente paciente) throws PacienteException, MedicoNoRegistradoException {
        this.registroPacienteService.atiendePaciente(medico, paciente);
        return this;
    }

    public Sistema internaPaciente(Paciente paciente, Habitacion habitacion) throws PacienteException {
        this.registroPacienteService.internaPaciente(paciente, habitacion);
        return this;
    }

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
