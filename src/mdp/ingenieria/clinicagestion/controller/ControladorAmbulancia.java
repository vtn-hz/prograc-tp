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

public class ControladorAmbulancia extends Controlador implements Observer {
    
    private Observable ambulancia;

    private ControladorSimulacion controladorSimulacion;

    public ControladorAmbulancia( ControladorSimulacion controladorSimulacion ){
    	this.controladorSimulacion = controladorSimulacion;
        this.ambulancia = Simulation.getInstance().getAmbulancia();
        this.ambulancia.addObserver(this);
    }

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
    
    public void eventOperario( Operario operario  )
    {
    	new TaskInteraction(
			TaskInteraction.TASK_SOLICITAR_MANTENIMIENTO,
			operario,
			(Ambulancia) this.ambulancia
    	);
    }
}
