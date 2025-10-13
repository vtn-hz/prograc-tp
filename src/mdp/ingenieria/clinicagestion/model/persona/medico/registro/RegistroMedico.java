package mdp.ingenieria.clinicagestion.model.persona.medico.registro;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representa el registro de consultas realizadas por un médico.
 */
public class RegistroMedico {
    private IMedico medico;
    private ArrayList<MedicoConsulta> consultasEfectuadas;

    /**
     * Constructor de un nuevo registro para el médico especificado.
     *
     * <b>pre:</b> el médico no debe ser nulo <br>
     * <b>post:</b> se crea un registro vacío para almacenar las consultas del médico
     *
     * @param medico instancia de IMedico cuyos datos serán registrados
     */
    public RegistroMedico(IMedico medico) {
        this.medico = medico;
        this.consultasEfectuadas = new ArrayList<>();
    }

    /**
     * Retorna el médico asociado a este registro.
     * <b>post:</b> devuelve la referencia al médico registrado
     *
     * @return objeto IMedico vinculado al registro
     */
    public IMedico getMedico() {
        return medico;
    }

    /**
     * Retorna la lista completa de consultas efectuadas por el médico.
     * <b>post:</b> devuelve todas las consultas registradas hasta el momento
     *
     * @return lista de objetos MedicoConsulta con las consultas realizadas
     */
    public ArrayList<MedicoConsulta> getConsultasEfectuadas() {
        return consultasEfectuadas;
    }

    /**
     * Retorna una lista de consultas efectuadas entre dos fechas dadas (inclusive).
     *
     * <b>pre:</b> las fechas inicial y final no deben ser nulas y la inicial debe ser menor o igual a la final <br>
     * <b>post:</b> devuelve todas las consultas realizadas en el rango de fechas especificado
     *
     * @param fechai        fecha y hora de inicio del rango
     * @param fechaf        fecha y hora de fin del rango
     * @return lista de consultas dentro del rango temporal indicado
     */
    public ArrayList<MedicoConsulta> getConsultasEfectuadasByFecha(LocalDateTime fechai, LocalDateTime fechaf) {
        ArrayList<MedicoConsulta> rangoConsultas = new ArrayList<>();

        for (MedicoConsulta cons : consultasEfectuadas) {
            LocalDateTime fecha = cons.getFecha();
            if ((fecha.isEqual(fechai) || fecha.isAfter(fechai)) && (fecha.isEqual(fechaf) || fecha.isBefore(fechaf))) {
                rangoConsultas.add(cons);
            }
        }

        return rangoConsultas;
    }

    /**
     * Agrega una nueva consulta al registro del médico.
     *
     * <b>pre:</b> la fecha y el nombre del paciente no deben ser nulos ni vacíos <br>
     * <b>post:</b> se agrega una nueva consulta al listado del médico
     *
     * @param fecha         fecha y hora en que se realizó la consulta
     * @param nombre        nombre completo del paciente atendido
     */
    public void addConsulta(LocalDateTime fecha, String nombre) {
        MedicoConsulta cons = new MedicoConsulta(fecha, nombre, medico.getHonorario());
        consultasEfectuadas.add(cons);
    }
}
