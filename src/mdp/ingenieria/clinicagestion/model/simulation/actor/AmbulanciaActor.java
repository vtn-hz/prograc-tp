package mdp.ingenieria.clinicagestion.model.simulation.actor;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.actoraction.AmbulanciaActorAction;


public class AmbulanciaActor extends Actor {

	private AmbulanciaMock ambulancia;
	
	private AmbulanciaActorAction action;
	
	public AmbulanciaActor(AmbulanciaMock ambulancia, AmbulanciaActorAction action) {
		super();
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
	
}
