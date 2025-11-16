package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class AmbulanceStateMessage {

    private Persona solicitante;

    private String message;
    
    private int status;

    /**
     * Crea un mensaje con solicitante asociado.
     *
     * <b>pre:</b> message no debe ser nulo; status debe ser un código válido <br>
     * <b>post:</b> el mensaje queda inicializado con texto, estado y solicitante
     *
     * @param message texto descriptivo del evento
     * @param status  estado numérico
     * @param solicitante persona relacionada con la acción
     */
    public AmbulanceStateMessage(String message, int status, Persona solicitante) {
        this.message = message;
        this.status = status;
        this.solicitante = solicitante;
    }

    /**
     * Crea un mensaje sin solicitante asociado.
     *
     * <b>pre:</b> message no debe ser nulo; status debe ser un código válido <br>
     * <b>post:</b> el mensaje queda inicializado con texto y estado
     *
     * @param message texto descriptivo del evento
     * @param status  estado numérico
     */
    public AmbulanceStateMessage(String message, int status){
        this.message = message;
        this.status = status;
    }

    /**
     * Indica si el mensaje tiene un solicitante asociado.
     *
     * @return true si existe solicitante, false en caso contrario
     */
    public boolean hasSolicitante() {
        return solicitante != null;
    }
    /**
     * Devuelve la persona solicitante asociada al mensaje.
     *
     * @return solicitante o null si no hay uno
     */
    public Persona getSolicitante() {
        return solicitante;
    }
    /**
     * Devuelve el mensaje descriptivo del evento.
     *
     * @return texto del mensaje
     */
    public String getMessage() {
        return message;
    }
    /**
     * Devuelve el estado numérico asociado al mensaje.
     *
     * @return estado de la ambulancia
     */
    public int getState () {
    	return status;
    }
}
