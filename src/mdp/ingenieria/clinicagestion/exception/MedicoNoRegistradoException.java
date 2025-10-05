package mdp.ingenieria.clinicagestion.exception;

public class MedicoNoRegistradoException extends Exception {
    private int matricula;

    public MedicoNoRegistradoException(int matricula) {
        super("No se encontró un médico registrado con matrícula: " + matricula);
        this.matricula = matricula;
    }

    public int getNumeroMatriculaBuscada() {
        return matricula;
    }
}

