package mdp.ingenieria.clinicagestion.model.clinica.salaespera;

import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Representa la sala de espera privada de la clínica.
 * Solo puede estar ocupada por un paciente a la vez.
 * Implementa el patrón Singleton para asegurar una única instancia en el sistema.
 */
public class SalaEsperaPrivada {
    private Paciente paciente;
    private static SalaEsperaPrivada _instancia;

    /**
     * Retorna la única instancia de SalaEsperaPrivada.
     * Si aún no existe, la crea.
     *
     * <b>pre:</b> no requiere inicialización previa <br>
     * <b>post:</b> garantiza una única instancia
     *
     * @return instancia única de SalaEsperaPrivada
     */
    public static SalaEsperaPrivada getInstance() {
        if (_instancia == null) {
            _instancia = new SalaEsperaPrivada();
        }
        return _instancia;
    }

    /**
     * Asigna un paciente a la sala de espera privada.
     *
     * <b>pre:</b> paciente no debe ser nulo y la sala no debe estar ocupada <br>
     * <b>post:</b> el paciente queda registrado en la sala
     *
     * @param paciente      paciente que ocupará la sala de espera privada
     */
    public void ocupar(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el paciente actualmente en la sala de espera privada.
     *
     * <b>pre:</b> la sala puede estar ocupada o vacía <br>
     * <b>post:</b> devuelve el paciente asignado o null si la sala está vacía
     *
     * @return el Paciente actual, o null si la sala está libre
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Libera la sala de espera privada, eliminando la referencia al paciente actual.
     *
     * <b>pre:</b> puede estar ocupada o vacía <br>
     * <b>post:</b> la sala queda libre
     */
    public void desocupar() {
        this.paciente = null;
    }

    /**
     * Indica si la sala de espera privada se encuentra ocupada.
     *
     * <b>pre:</b> la instancia de sala debe estar inicializada <br>
     * <b>post:</b> devuelvee true si hay un paciente en la sala, o false en caso contrario
     *
     * @return true si la sala está ocupada; false si está libre
     */
    public boolean estaOcupada() {
        return this.paciente != null;
    }
}
