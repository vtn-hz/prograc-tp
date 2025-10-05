package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoEncontradoException extends PacienteException 
{
    public PacienteNoEncontradoException(Paciente paciente)
	{
        super("El paciente no se encuentra en las instalaciones de la clínica", paciente);
    }
}
