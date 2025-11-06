package mdp.ingenieria.clinicagestion.model.simulation.actor;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;


public class AmbulanciaActor extends Actor {
	
	private AmbulanciaMock ambulancia;

	public AmbulanciaActor(int averageTaskTime, AmbulanciaMock ambulancia) {
		super(averageTaskTime);
		this.ambulancia = ambulancia;
	}
	
	@Override
	public void run() {
		while (Simulation.getInstance().hasTemporalActorsWorking())
		{	
			this.runTask();
		}
		
		Simulation.getInstance().setRunning(false);
	}

	@Override	
	public void runTask() {
		ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
		this.ambulancia.retornoAutomatico();
	}
	
}
