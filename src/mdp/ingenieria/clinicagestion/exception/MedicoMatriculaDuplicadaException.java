package mdp.ingenieria.clinicagestion.exception;

public class MedicoMatriculaDuplicadaException extends Exception {
    private int matricula;

    public MedicoMatriculaDuplicadaException(int matricula) {
        super("Ya existe un médico registrado con la matrícula: " + matricula);
        this.matricula = matricula;
    }

    public int getNumeroMatriculaDuplicada() {
        return matricula;
    }
}
