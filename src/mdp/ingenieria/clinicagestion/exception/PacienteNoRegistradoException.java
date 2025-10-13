package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;
/**
 * Excepción que se lanza cuando se intenta acceder o realizar una operación sobre un Paciente que no está registrado.
 * Extiende PacienteException.
 */
public class PacienteNoRegistradoException extends PacienteException
{
    /**
     * Crea una nueva excepción indicando que el paciente no se encuentra registrado.
     * <b>post:</b> se crea una excepción con un mensaje descriptivo y el paciente asociado al error
     *
     * @param paciente      instancia del Paciente que no está registrado
     */
    public PacienteNoRegistradoException(Paciente paciente)
	{
        super("El paciente no se encuentra registrado", paciente);
    }
}
