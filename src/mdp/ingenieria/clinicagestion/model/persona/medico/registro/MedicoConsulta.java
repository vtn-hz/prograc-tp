package mdp.ingenieria.clinicagestion.model.persona.medico.registro;

import java.time.LocalDateTime;

/**
 * Representa una consulta médica.
 */
public class MedicoConsulta {
    private LocalDateTime fecha;
    private String nombrePaciente;
    private double honorario;

    /**
     * Constructor de MedicoConsulta con los datos especificados.
     *
     * <b>pre:</b> la fecha y el nombre del paciente no deben ser nulos ni vacíos, y el honorario debe ser mayor que cero <br>
     * <b>post:</b> se crea un registro de consulta médica con los valores proporcionados
     *
     * @param fecha                 fecha y hora en que se realizó la consulta
     * @param nombrePaciente        nombre completo del paciente atendido
     * @param honorario             monto del honorario correspondiente a la consulta
     */
    public MedicoConsulta(LocalDateTime fecha, String nombrePaciente, double honorario) {
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.honorario = honorario;
    }

    /**
     * Retorna la fecha y hora de la consulta.
     * <b>post:</b> devuelve la fecha registrada de la consulta
     *
     * @return fecha y hora de la consulta
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Retorna el nombre del paciente atendido.
     * <b>post:</b> devuelve el nombre completo del paciente
     *
     * @return nombre del paciente asociado a la consulta
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Retorna el honorario asociado a la consulta.
     * <b>post:</b> devuelve el monto del honorario correspondiente
     *
     * @return honorario percibido por la consulta
     */
    public double getHonorario() {
        return honorario;
    }
}
