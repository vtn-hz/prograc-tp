package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoEncontradoException extends Exception{
    private Paciente paciente;

    public PacienteNoEncontradoException(Paciente paciente){
        super("El paciente no se encuentra en las instalaciones de la clínica");
        this.paciente = paciente;
    }

    public PacienteNoEncontradoException(String msg, Paciente paciente){
        super(msg);
        this.paciente = paciente;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }
}
