package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class AmbulanceStateMessage {

    private Persona solicitante;

    private String message;

    public AmbulanceStateMessage(String message, Persona solicitante) {
        this.message = message;
        this.solicitante = solicitante;
    }

    public AmbulanceStateMessage(String message){
        this.message = message;
    }

    public boolean hasSolicitante() {
        return solicitante != null;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public String getMessage() {
        return message;
    }
}
