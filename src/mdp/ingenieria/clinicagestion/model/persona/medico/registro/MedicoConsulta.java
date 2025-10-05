package mdp.ingenieria.clinicagestion.model.persona.medico.registro;

import java.time.LocalDateTime;

public class MedicoConsulta {
    private LocalDateTime fecha;
    private String nombrePaciente;
    private double honorario;

    public MedicoConsulta(LocalDateTime fecha, String nombrePaciente, double honorario) {
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.honorario = honorario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public double getHonorario() {
        return honorario;
    }
}
