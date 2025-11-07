package mdp.ingenieria.clinicagestion.model.simulation.actor;

import java.util.Random;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;


public class AsociadoActor extends Actor {
	
	private static final int SOLICITA_DOMICILIO = 0;
	
	private static final int SOLICITA_AMBULANCIA = 1;
	
	private static final int AVAILABLE_ACTION_AMOUNT = 2;
	
	private Random random;
	
	private Asociado asociado;
	
	private int interactionCount;

	public AsociadoActor(int averageTaskTime, int interactionCount, Asociado asociado) {
		super(averageTaskTime);
		this.asociado = asociado;
		this.interactionCount = interactionCount;
		this.random = new Random();
	}
	
	@Override
	public void run() {
		while (this.interactionCount > 0 && Simulation.getInstance().isRunning())
		{	
			this.runTask();
			this.interactionCount--;
		}
		
		Simulation.getInstance().temporalThreadFinalized();
	}

	@Override
	public void runTask() {
		ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
		
		int actionSelected = this.random.nextInt(AVAILABLE_ACTION_AMOUNT);
		assert actionSelected > 1;
		
		switch(actionSelected) 
		{
			case SOLICITA_DOMICILIO: this.asociado.solicitaAmbulanciaDomicilio(); 	break;
			case SOLICITA_AMBULANCIA: this.asociado.solicitaAmbulancia(); 			break;
		}
	}
	
	/**
	 * cuando el usuario interactua con la simulacion, se deberia crear otro AsociadoActor con
	 * la implementacion actual, ya que los hilos viven hasta que se completen sus tareas.
	 */
	
	/**
	 * nov1 15:30
	 * como podemos solucionar si se queda para siempre esperando
	 * el estado de un translado por ejemplo que no va a llegar
	 * (como podemos solucionar los deadlock de la simulacion?)
	 */
	
	/**
	 * nov1 16:47
	 * Retorno automático a la clínica (evento temporal del sistema).
	 * creo que esto podria solucionarlo 
	 */
	
}
