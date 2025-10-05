package mdp.ingenieria.clinicagestion.excepciones;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNoIngresadoException extends PacienteNoEncontradoException{
    private Paciente paciente;

    public PacienteNoIngresadoException(Paciente paciente) {
        super("El paciente no fue encontrado en ninguna sala de espera", paciente);
        this.paciente = paciente;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }
}
