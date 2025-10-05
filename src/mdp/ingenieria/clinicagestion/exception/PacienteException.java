package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteException extends Exception
{
	
    private Paciente paciente;

    public PacienteException(String msg, Paciente paciente)
    {
    	super(msg);
    	this.paciente = paciente;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }
	
}
