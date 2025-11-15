package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class TaskInteraction extends Thread {
	
	public static final int TASK_SOLICITA_DOMICILIO = 0;
	
	public static final int TASK_SOLICITA_AMBULANCIA = 1;
	
	public static final int TASK_SOLICITAR_MANTENIMIENTO = 2;
	
	private int actionTagTask;
	
	private Ambulancia ambulancia;
	
	private Persona persona;

	public TaskInteraction(int actionTagTask, Persona persona, Ambulancia ambulancia)
	{
		super();
		this.actionTagTask = actionTagTask;
		this.ambulancia = ambulancia;
		this.persona = persona;
		
		Simulation.getInstance().temporalThreadStarted();
		this.start();
	}

	@Override
	public void run () {
		this.runTask();	
		Simulation.getInstance().temporalThreadFinalized();
	}
	
	public void runTask () {
		ThreadUtil.simulateTimeMedio( Simulation.getInstance().getTaskTime() / 4 );

		switch( this.actionTagTask ) {
			case TaskInteraction.TASK_SOLICITA_AMBULANCIA: 
				this.ambulancia.solicitarTraslado((Asociado) this.persona);
			break;
			
			case TaskInteraction.TASK_SOLICITA_DOMICILIO:
				this.ambulancia.solicitarAtencionDomicilio((Asociado) this.persona);
			break;
			
			case TaskInteraction.TASK_SOLICITAR_MANTENIMIENTO:
				this.ambulancia.solicitarMantenimiento((Operario) this.persona);
			break;
		}
		
		ThreadUtil.simulateTimeMedio( Simulation.getInstance().getTaskTime() / 4 );
	}
}
