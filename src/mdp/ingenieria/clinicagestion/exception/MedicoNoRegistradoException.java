package mdp.ingenieria.clinicagestion.exception;

/**
 * Excepción que se lanza cuando se intenta acceder o modificar un médico
 * que no se encuentra registrado.
 */
public class MedicoNoRegistradoException extends Exception {
    private int matricula;

    /**
     * Crea una nueva excepción indicando que no se encontró un médico con la matrícula especificada.
     *
     * <b>pre:</b> la matrícula debe ser un número válido y positivo <br>
     * <b>post:</b> se crea una excepción con un mensaje descriptivo del error
     *
     * @param matricula número de matrícula del médico que no fue encontrado
     */
    public MedicoNoRegistradoException(int matricula) {
        super("No se encontró un médico registrado con matrícula: " + matricula);
        this.matricula = matricula;
    }

    /**
     * Retorna el número de matrícula del médico que se intentó buscar.
     * <b>post:</b> devuelve el número de matrícula utilizado en la búsqueda
     *
     * @return número de matrícula del médico no registrado
     */
    public int getNumeroMatriculaBuscada() {
        return matricula;
    }
}

