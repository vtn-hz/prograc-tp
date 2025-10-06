package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Excepción que se lanza cuando un paciente no se encuentra en las instalaciones de la clínica.
 */
public class PacienteNoEncontradoException extends PacienteException {

    /**
     * Crea una excepción indicando que el paciente no se encuentra en la clínica.
     * <b>post:</b> la excepción queda inicializada con un mensaje por defecto y el paciente asociado
     *
     * @param paciente instancia del paciente que no fue encontrada
     */
    public PacienteNoEncontradoException(Paciente paciente) {
        super("El paciente no se encuentra en las instalaciones de la clínica", paciente);
    }

    /**
     * Crea una excepción personalizada indicando que el paciente no fue encontrado.
     * <b>pre:</b> msg no nulo ni vacío <br>
     * <b>post:</b> la excepción queda inicializada con el mensaje especificado y el paciente asociado
     *
     * @param msg      mensaje personalizado de error
     * @param paciente instancia del paciente que no fue encontrada
     */
    public PacienteNoEncontradoException(String msg, Paciente paciente) {
        super(msg, paciente);
    }
}
