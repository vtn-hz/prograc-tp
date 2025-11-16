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

    /**
     * Crea una nueva interacción y la inicia automáticamente.
     * <b>pre:</b> ambulancia y persona no deben ser nulos; actionTagTask debe ser válido <br>
     * <b>post:</b> se incrementa el contador de hilos de la simulación y comienza la ejecución
     *
     * @param actionTagTask tipo de acción a realizar
     * @param persona persona que realiza la solicitud
     * @param ambulancia ambulancia a la que se dirige la solicitud
     */
    public TaskInteraction(int actionTagTask, Persona persona, Ambulancia ambulancia)
	{
		super();
		this.actionTagTask = actionTagTask;
		this.ambulancia = ambulancia;
		this.persona = persona;
		
		Simulation.getInstance().temporalThreadStarted();
		this.start();
	}

    /**
     * Ejecuta la interacción completa y notifica a la simulación al finalizar.
     */
	@Override
	public void run () {
		this.runTask();	
		Simulation.getInstance().temporalThreadFinalized();
	}

    /**
     * Realiza la acción correspondiente al tipo indicado.
     *
     * <b>post:</b> se simula tiempo antes y después de ejecutar la acción
     */
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
