package mdp.ingenieria.clinicagestion.exception;

/**
 * Excepción que se lanza cuando se intenta registrar un médico cuya matrícula
 * ya existe en el sistema. Permite identificar el número de matrícula duplicado.
 */
public class MedicoMatriculaDuplicadaException extends Exception {
    private int matricula;

    /**
     * Crea una nueva excepción indicando que la matrícula del médico ya está registrada.
     *
     * <b>pre:</b> la matrícula debe ser un número válido y positivo <br>
     * <b>post:</b> se crea una excepción con un mensaje descriptivo del error
     *
     * @param matricula     número de matrícula duplicado que causó la excepción
     */
    public MedicoMatriculaDuplicadaException(int matricula) {
        super("Ya existe un médico registrado con la matrícula: " + matricula);
        this.matricula = matricula;
    }

    /**
     * Retorna el número de matrícula duplicado que provocó la excepción.
     * <b>post:</b> devuelve el valor de la matrícula conflictiva
     *
     * @return número de matrícula duplicado
     */
    public int getNumeroMatriculaDuplicada() {
        return matricula;
    }
}
