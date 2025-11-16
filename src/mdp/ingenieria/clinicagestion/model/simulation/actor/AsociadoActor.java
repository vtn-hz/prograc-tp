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

    /**
     * Crea el actor de un asociado.
     *
     * <b>pre:</b> interactionCount >= 0 y asociado no debe ser nulo <br>
     * <b>post:</b> el actor queda preparado para ejecutar acciones aleatorias
     *
     * @param interactionCount cantidad de acciones a realizar
     * @param asociado objeto asociado que realizará las solicitudes
     */
	public AsociadoActor(int interactionCount, Asociado asociado) {
		super();
		this.asociado = asociado;
		this.interactionCount = interactionCount;
		this.random = new Random();
	}

    /**
     * Ciclo principal del actor: ejecuta acciones mientras tenga interacciones pendientes
     * y la simulación continúe activa.
     *
     * <b>post:</b> al finalizar, notifica a la simulación que terminó su ejecución
     */
    @Override
	public void run() {
		while (this.interactionCount > 0 && Simulation.getInstance().isRunning())
		{	
			this.runTask();
			this.interactionCount--;
		}
		
		Simulation.getInstance().temporalThreadFinalized( this.asociado );
	}

    /**
     * Ejecuta una acción aleatoria (solicitar domicilio o solicitar ambulancia).
     *
     * <b>pre:</b> la simulación debe estar en ejecución <br>
     * <b>post:</b> el asociado realiza una solicitud y se simula el tiempo correspondiente
     */
	@Override
	public void runTask() {
		int actionSelected = this.random.nextInt(AVAILABLE_ACTION_AMOUNT);

		ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
		
		switch(actionSelected) 
		{
			case SOLICITA_DOMICILIO: this.asociado.solicitaAmbulanciaDomicilio(); 	break;
			case SOLICITA_AMBULANCIA: this.asociado.solicitaAmbulancia(); 			break;
		}
		
		ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
	}	
	
}
