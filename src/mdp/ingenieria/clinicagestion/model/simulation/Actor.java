package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class Actor extends Thread {

	private volatile boolean simulationRunning;
	
	public Actor(){}
	
	@Override
	public abstract void run() ;

	public boolean isSimulationRunning() {
		return simulationRunning;
	}

	public void setSimulationRunning(boolean simulationRunning) {
		this.simulationRunning = simulationRunning;
	}
	
}
