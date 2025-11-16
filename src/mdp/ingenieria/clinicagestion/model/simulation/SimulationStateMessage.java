package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class SimulationStateMessage {

    private final String status;
    
    private final Persona actorModel;

    /**
     * Crea un mensaje de estado sin actor asociado.
     *
     * @param status estado de la simulación
     */
    public SimulationStateMessage(String status) {
        this.status = status;
        this.actorModel = null;
    }

    /**
     * Crea un mensaje de estado con un actor asociado.
     *
     * @param status estado de la simulación
     * @param actorModel persona cuyo actor produjo el evento
     */
    public SimulationStateMessage(String status, Persona actorModel) {
        this.status = status;
        this.actorModel = actorModel;
    }

    /**
     * Devuelve el estado informado por el mensaje.
     *
     * @return estado actual de la simulación
     */
    public String getStatus() {
        return status;
    }

    /**
     * Devuelve la persona asociada al evento, si existe.
     *
     * @return modelo de persona o null si no se incluyó
     */
    public Persona getActorModel() {
        return actorModel;
    }

    /**
     * Indica si el mensaje contiene un modelo de actor.
     *
     * @return true si incluye actorModel, false en caso contrario
     */
    public boolean hasActorModel() {
        return  actorModel != null;
    }
}
