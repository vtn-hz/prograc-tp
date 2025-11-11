package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class TaskInteraction extends Thread {
	
	private static final int TASK_SOLICITA_DOMICILIO = 0;
	
	private static final int TASK_SOLICITA_AMBULANCIA = 1;
	
	private static final int TASK_SOLICITAR_MANTENIMIENTO = 2;
	
	private int actionTagTask;
	
	private int averageTaskTime;
	
	private Ambulancia ambulancia;

	public TaskInteraction(int actionTagTask, int averageTaskTime, Ambulancia ambulancia)
	{
		super();
		this.actionTagTask = actionTagTask;
		this.averageTaskTime = averageTaskTime;
		this.ambulancia = ambulancia;
		
		Simulation.getInstance().temporalThreadStarted();
		this.start();
	}

    //TODO: add person param to send to ambulance methods
	@Override
	public void run () {
		ThreadUtil.simulateTimeMedio( averageTaskTime );
		this.runTask();	
		Simulation.getInstance().temporalThreadFinalized();
	}
	
	public void runTask () {
		ThreadUtil.simulateTimeMedio( averageTaskTime );
		
		switch( this.actionTagTask ) {
			case TaskInteraction.TASK_SOLICITA_AMBULANCIA: 
				this.ambulancia.solicitarTraslado(null);
			break;
			
			case TaskInteraction.TASK_SOLICITA_DOMICILIO:
				this.ambulancia.solicitarAtencionDomicilio(null);
			break;
			
			case TaskInteraction.TASK_SOLICITAR_MANTENIMIENTO:
				this.ambulancia.solicitarMantenimiento(null);
			break;
		}
	}
}
