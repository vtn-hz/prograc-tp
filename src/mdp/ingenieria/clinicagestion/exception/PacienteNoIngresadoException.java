package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoIngresadoException extends PacienteException
{
    public PacienteNoIngresadoException(Paciente paciente) 
    {
        super("El paciente no fue encontrado en ninguna sala de espera", paciente);
    }
}
