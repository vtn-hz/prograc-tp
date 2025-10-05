package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoAtendidoException extends PacienteException
{
    public PacienteNoAtendidoException(Paciente paciente) 
    {
        super("El paciente no fue atendido", paciente);
    }
}
