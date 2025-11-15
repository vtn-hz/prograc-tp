package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class Actor extends Thread {
	
	public static final int PENDING = 0;
	
	public static final int RUNNING = 1;
	
	public static final int TERMINATED = 2;

	public Actor() {
	}

	@Override
	public abstract void run() ;

	public abstract void runTask() ;

	protected int getAverageTaskTime() {
		return Simulation.getInstance().getTaskTime();
	}
}
