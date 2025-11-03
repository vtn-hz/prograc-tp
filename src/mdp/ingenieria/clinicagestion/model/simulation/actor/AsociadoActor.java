package mdp.ingenieria.clinicagestion.model.simulation.actor;

import java.util.Queue;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.actoraction.AsociadoActorAction;

public class AsociadoActor extends Actor {

	private Asociado asociado;
	
	private Queue<AsociadoActorAction> actionQueue;
	
	public AsociadoActor(Asociado asociado, Queue<AsociadoActorAction> actionQueue) {
		super();
		assert asociado != null;
		assert actionQueue != null;
		this.asociado = asociado;
		this.actionQueue = actionQueue;
	}
	
	@Override
	public void run() {
		while (!actionQueue.isEmpty() && this.isSimulationRunning())
		{	
			AsociadoActorAction action = this.actionQueue.poll();
			action.execute( this.asociado );
		}
	}
	
	public void addActorAction (AsociadoActorAction action) {
		this.actionQueue.add(action);
	}
	
	public void clearActions () {
		this.actionQueue.clear();
	}

	/**
	 * cuando el usuario interactua con la simulacion, se deberia crear otro AsociadoActor con
	 * la implementacion actual, ya que los hilos viven hasta que se completen sus tareas.
	 */
	
	/**
	 * nov1 15:30
	 * como podemos solucionar si se queda para siempre esperando
	 * el estado de un translado por ejemplo que no va a llegar
	 * (como podemos solucionar los deadlock de la simulacion?)
	 */
	
	/**
	 * nov1 16:47
	 * Retorno automático a la clínica (evento temporal del sistema).
	 * creo que esto podria solucionarlo 
	 */
}
