package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoRegistradoException extends PacienteException
{
    public PacienteNoRegistradoException(Paciente paciente)
	{
        super("El paciente no se encuentra registrado", paciente);
    }
}
