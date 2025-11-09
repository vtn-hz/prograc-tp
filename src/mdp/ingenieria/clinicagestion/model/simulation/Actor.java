package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class Actor extends Thread {

	private int averageTaskTime;
	
	public Actor( int averageTaskTime ){
		this.averageTaskTime = averageTaskTime;
	}
	
	@Override
	public abstract void run() ;
	
	public abstract void runTask() ;

	public int getAverageTaskTime() {
		return averageTaskTime;
	}
}
