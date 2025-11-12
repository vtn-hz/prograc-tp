package mdp.ingenieria.clinicagestion.model.simulation.actor;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;


public class AmbulanciaActor extends Actor {
	
	private Ambulancia ambulancia;

	public AmbulanciaActor(int averageTaskTime, Ambulancia ambulancia) {
		super(averageTaskTime);
		this.ambulancia = ambulancia;
	}
	
	@Override
	public void run() {
		while (Simulation.getInstance().hasTemporalThreadWorking())
		{	
			this.runTask();
		}
		
		Simulation.getInstance().setStatus( Simulation.STATE_TERMINATED );
	}

	@Override	
	public void runTask() {
		ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
		this.ambulancia.retornoAutomatico();
	}
	
}
