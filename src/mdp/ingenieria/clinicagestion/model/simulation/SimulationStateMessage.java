package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class SimulationStateMessage {

    private final String status;
    
    private final Persona actorModel;

    public SimulationStateMessage(String status) {
        this.status = status;
        this.actorModel = null;
    }

    public SimulationStateMessage(String status, Persona actorModel) {
        this.status = status;
        this.actorModel = actorModel;
    }

    public String getStatus() {
        return status;
    }

    public Persona getActorModel() {
        return actorModel;
    }

    public boolean hasActorModel() {
        return  actorModel != null;
    }
}
