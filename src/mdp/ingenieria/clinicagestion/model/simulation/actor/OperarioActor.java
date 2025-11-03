package mdp.ingenieria.clinicagestion.model.simulation.actor;

import java.util.Queue;

import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.actoraction.OperarioActorAction;

public class OperarioActor extends Actor {

	private Operario operario;
	
	private Queue<OperarioActorAction> actionQueue;
	
	public OperarioActor(Operario operario, Queue<OperarioActorAction> actionQueue) {
		assert operario != null;
		assert actionQueue != null;
		this.operario = operario;
		this.actionQueue = actionQueue;
	}
	
	@Override
	public void run() {
		while (!actionQueue.isEmpty() && this.isSimulationRunning())
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
	
	/**
	 * cuando el usuario interactua con la simulacion, se deberia crear otro AsociadoActor con
	 * la implementacion actual, ya que los hilos viven hasta que se completen sus tareas.
	 */
}
