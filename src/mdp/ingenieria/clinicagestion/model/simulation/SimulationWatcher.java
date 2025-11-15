package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class SimulationWatcher extends Thread {

	public SimulationWatcher() {
	}

	@Override
	public abstract void run();

	public abstract void runTask();

	protected int getAverageTaskTime() {
		return Simulation.getInstance().getTaskTime();
	}
}
