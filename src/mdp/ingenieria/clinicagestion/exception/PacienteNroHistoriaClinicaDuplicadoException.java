package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNroHistoriaClinicaDuplicadoException extends PacienteException
{
    public PacienteNroHistoriaClinicaDuplicadoException(Paciente paciente)
	{
        super("El paciente ya se encuentra registrado", paciente);
    }
}
