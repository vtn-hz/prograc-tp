package mdp.ingenieria.clinicagestion.model.persona;

/**
 * Double dispatch para resolver la ocupación de salas de espera según el tipo de paciente involucrado (niño, joven, mayor).
 */
public interface IProtocoloOcuparSala {
    /**
     * Inicia el proceso de ocupación de una sala de espera para el paciente nuevo.
     * <b>pre:</b> deben existir instancias válidas de las salas de espera del sistema <br>
     * <b>post:</b> el paciente queda ubicado en una sala válida o se delega la resolución
     */
    void ocuparSala();
    /**
     * Resuelve la ubicación cuando el paciente nuevo es niño.
     *
     * <b>pre:</b> ocuparSala implementa este protocolo y representa al otro paciente involucrado <br>
     * <b>post:</b> se decide y ejecuta la ubicación de ambos pacientes según la regla Niño y tipo actual
     *
     * @param ocuparSala el otro paciente (niño) participante del conflicto de ocupación
     */
    void ocuparSalaNino(IProtocoloOcuparSala ocuparSala);
    /**
     * Resuelve la ubicación cuando el paciente nuevo es joven.
     *
     * <b>pre:</b> ocuparSala implementa este protocolo y representa al otro paciente involucrado <br>
     * <b>post:</b> se decide y ejecuta la ubicación de ambos pacientes según la regla Joven y tipo actual
     *
     * @param ocuparSala el otro paciente (joven) participante del conflicto de ocupación
     */
    void ocuparSalaJoven(IProtocoloOcuparSala ocuparSala);
    /**
     * Resuelve la ubicación cuando el paciente nuevo es mayor.
     *
     * <b>pre:</b> ocuparSala implementa este protocolo y representa al otro paciente involucrado <br>
     * <b>post:</b> se decide y ejecuta la ubicación de ambos pacientes según la regla mayor y tipo actual
     *
     * @param ocuparSala el otro paciente (mayor) participante del conflicto de ocupación
     */
    void ocuparSalaMayor(IProtocoloOcuparSala ocuparSala);
}
