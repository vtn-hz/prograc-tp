package mdp.ingenieria.clinicagestion.model.actor;

import java.util.Queue;

import mdp.ingenieria.clinicagestion.model.actor.actoraction.OperarioActorAction;
import mdp.ingenieria.clinicagestion.model.persona.Operario;

public class OperarioActor extends Thread {

	private Operario operario;
	
	private Queue<OperarioActorAction> actionQueue;
	
	public OperarioActor(Operario operario, Queue<OperarioActorAction> actionQueue) {
		assert operario != null;
		assert actionQueue != null;
		this.operario = operario;
		this.actionQueue = actionQueue;
	}
	
	/**
	 * cuando el usuario interactua con la simulacion, se deberia crear otro AsociadoActor con
	 * la implementacion actual, ya que los hilos viven hasta que se completen sus tareas.
	 */
	
	
	@Override
	public void run() {
		while (!actionQueue.isEmpty())
		{	
			OperarioActorAction action = this.actionQueue.poll();
			action.execute( this.operario );
		}
	}
	
	public void addActorAction (OperarioActorAction action) {
		this.actionQueue.add(action);
	}
	
	public void clearActions () {
		this.actionQueue.clear();
	}
}
