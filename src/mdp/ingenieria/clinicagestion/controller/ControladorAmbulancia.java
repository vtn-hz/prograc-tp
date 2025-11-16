package mdp.ingenieria.clinicagestion.controller;

import java.util.Observable;
import java.util.Observer;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.model.simulation.TaskInteraction;

import mdp.ingenieria.clinicagestion.model.ambulancia.AmbulanceStateMessage;

/**
 * Controlador responsable de observar el estado de la ambulancia
 * y actualizar la simulación según los cambios notificados.
 * También permite disparar eventos de mantenimiento solicitados por un operario.
 */
public class ControladorAmbulancia extends Controlador implements Observer {
    
    private Observable ambulancia;

    private ControladorSimulacion controladorSimulacion;

    /**
     * Crea un nuevo controlador de ambulancia y lo registra como observador
     * de la instancia de ambulancia de la simulación.
     *
     * <b>pre:</b> controladorSimulacion no debe ser nulo, y la simulación debe tener una ambulancia inicializada <br>
     * <b>post:</b> el controlador queda asociado a controladorSimulacion
     * y registrado como observador de la ambulancia de la simulación
     *
     * @param controladorSimulacion controlador de la simulación al que se reportan los cambios
     */
    public ControladorAmbulancia( ControladorSimulacion controladorSimulacion ){
    	this.controladorSimulacion = controladorSimulacion;
        this.ambulancia = Simulation.getInstance().getAmbulancia();
        this.ambulancia.addObserver(this);
    }

    /**
     * Maneja las notificaciones de cambio de estado provenientes de la ambulancia.
     * Actualiza el estado del asociado involucrado (si lo hubiera), registra la operación
     * y refleja el estado actual de la ambulancia en el controlador de simulación.
     *
     * <b>pre:</b> o debe ser la misma instancia observable almacenada en ambulancia,
     * y arg debe ser un objeto de tipo AmbulanceStateMessage <br>
     * <b>post:</b> si hay solicitante, se actualiza su estado en la simulación;
     * si el estado es RUNNING, se registra la operación; siempre se actualiza
     * el estado visible de la ambulancia en el controlador de simulación
     *
     * @param o   observable que emite la notificación (se espera que sea la ambulancia)
     * @param arg mensaje de estado de la ambulancia con la información del cambio
     */
    @Override
    public void update(Observable o, Object arg) {
        if (this.ambulancia == o) {
        	Ambulancia ambulancia = (Ambulancia) this.ambulancia;
        	AmbulanceStateMessage messageTransporter = (AmbulanceStateMessage) arg;
        	String prologue = "";
        	if (messageTransporter.hasSolicitante()) {
        		prologue += messageTransporter.getSolicitante().getNyA() + " - ";
        		Persona persona = messageTransporter.getSolicitante();
        		this.controladorSimulacion.updateAsociadoState(persona.getDni(), messageTransporter.getState());
        	}
        	
        	if (messageTransporter.getState() == Actor.RUNNING) {
        		this.controladorSimulacion.addOperation( prologue + messageTransporter.getMessage() );
        	}
        	
        	
        	this.controladorSimulacion.updateAmbulanciaState(ambulancia.getEstado().toString());
        }
    }

    /**
     * Dispara una interacción de solicitud de mantenimiento de la ambulancia
     * iniciada por un operario.
     *
     * <b>pre:</b> operario no debe ser nulo, y ambulancia debe estar correctamente inicializada <br>
     * <b>post:</b> se crea una nueva interacción de tarea para solicitar el mantenimiento
     * de la ambulancia, quedando registrada en la lógica de simulación
     *
     * @param operario operario que solicita la tarea de mantenimiento
     */
    public void eventOperario( Operario operario  )
    {
    	new TaskInteraction(
			TaskInteraction.TASK_SOLICITAR_MANTENIMIENTO,
			operario,
			(Ambulancia) this.ambulancia
    	);
    }
}
