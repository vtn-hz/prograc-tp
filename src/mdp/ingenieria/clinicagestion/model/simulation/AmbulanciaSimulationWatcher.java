package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.util.ThreadUtil;


public class AmbulanciaSimulationWatcher extends SimulationWatcher {

	public AmbulanciaSimulationWatcher() {
		super();
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
		ThreadUtil.simulateTime( this.getAverageTaskTime() * 2 );
		Simulation.getInstance().getAmbulancia().retornoAutomatico();
		ThreadUtil.simulateTime( this.getAverageTaskTime() * 2 );
	}

}
