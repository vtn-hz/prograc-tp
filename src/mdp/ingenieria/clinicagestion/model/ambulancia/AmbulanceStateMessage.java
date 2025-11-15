package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class AmbulanceStateMessage {

    private Persona solicitante;

    private String message;
    
    private int status;

    public AmbulanceStateMessage(String message, int status, Persona solicitante) {
        this.message = message;
        this.status = status;
        this.solicitante = solicitante;
    }

    public AmbulanceStateMessage(String message, int status){
        this.message = message;
        this.status = status;
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
    
    public int getState () {
    	return status;
    }
}
