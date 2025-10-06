package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Excepción que se lanza cuando se intenta realizar una operación sobre un Paciente que aún no ha sido atendido.
 * Extiende PacienteException
 */
public class PacienteNoAtendidoException extends PacienteException
{
    /**
     * Crea una nueva excepción indicando que el paciente no fue atendido.
     *
     * <b>pre:</b> el paciente debe estar correctamente instanciado <br>
     * <b>post:</b> se crea una excepción con un mensaje descriptivo y el paciente asociado al error
     *
     * @param paciente instancia del Paciente que no ha sido atendido
     */
    public PacienteNoAtendidoException(Paciente paciente) 
    {
        super("El paciente no fue atendido", paciente);
    }
}
