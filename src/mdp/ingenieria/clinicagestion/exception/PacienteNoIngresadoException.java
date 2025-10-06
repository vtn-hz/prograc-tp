package mdp.ingenieria.clinicagestion.exception;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Excepción que se lanza cuando un paciente no se encuentra en ninguna sala de espera de la clínica.
 */
public class PacienteNoIngresadoException extends PacienteNoEncontradoException {

    /**
     * Crea una excepción indicando que el paciente no fue encontrado en ninguna sala de espera.
     * <b>pre:</b> paciente no debe ser nulo <br>
     * <b>post:</b> la excepción queda inicializada con un mensaje descriptivo y el paciente asociado
     *
     * @param paciente instancia del paciente que no fue ingresado a la clínica
     */
    public PacienteNoIngresadoException(Paciente paciente) {
        super("El paciente no fue encontrado en ninguna sala de espera", paciente);
    }

    /**
     * Variante con mensaje personalizado.
     *
     * @param msg      mensaje personalizado de error
     * @param paciente instancia del paciente que no fue ingresado a la clínica
     */
    public PacienteNoIngresadoException(String msg, Paciente paciente) {
        super(msg, paciente);
    }
}
