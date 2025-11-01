package mdp.ingenieria.clinicagestion.model.actor;



import java.util.Queue;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.actor.actoraction.AmbulanciaActorAction;


public class AmbulanciaActor extends Thread {

	private AmbulanciaMock ambulancia;
	
	private AmbulanciaActorAction action;
	
	private volatile boolean simulationRunning;
	
	public AmbulanciaActor(AmbulanciaMock ambulancia, AmbulanciaActorAction action) {
		assert ambulancia != null;
		this.ambulancia = ambulancia;
		this.action = action;	
	}
	
	@Override
	public void run() {
		while (this.isSimulationRunning())
		{	
			this.action.execute(ambulancia);
		}
	}

	public boolean isSimulationRunning() {
		return simulationRunning;
	}

	public void setSimulationRunning(boolean simulationRunning) {
		this.simulationRunning = simulationRunning;
	}
	
}
