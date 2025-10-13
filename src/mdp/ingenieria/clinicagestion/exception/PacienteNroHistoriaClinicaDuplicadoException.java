package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Excepción que se lanza cuando se intenta registrar un Paciente cuyo número de historia clínica ya existe.
 * Extiende PacienteException
 */
public class PacienteNroHistoriaClinicaDuplicadoException extends PacienteException
{
    public PacienteNroHistoriaClinicaDuplicadoException(Paciente paciente)
	{
        super("El paciente ya se encuentra registrado", paciente);
    }
}
