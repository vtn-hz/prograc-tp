package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.util.ThreadUtil;


public class AmbulanciaSimulationWatcher extends SimulationWatcher {

    /**
     * Constructor por defecto.
     *
     * <b>post:</b> inicializa el watcher sin iniciar su ejecución
     */
	public AmbulanciaSimulationWatcher() {
		super();
	}

    /**
     * Ciclo principal: continúa ejecutando tareas de observación
     * mientras existan actores temporales en ejecución.
     *
     * <b>post:</b> al finalizar, marca la simulación como terminada
     */
	@Override
	public void run() {
		while (Simulation.getInstance().hasTemporalThreadWorking())
		{
			this.runTask();
		}

		Simulation.getInstance().setStatus( Simulation.STATE_TERMINATED );
	}

    /**
     * Ejecuta una tarea de vigilancia: espera un tiempo,
     * ordena el retorno automático de la ambulancia y espera nuevamente.
     *
     * <b>post:</b> puede provocar cambios de estado en la ambulancia
     */
	@Override
	public void runTask() {
		ThreadUtil.simulateTime( this.getAverageTaskTime() * 2 );
		Simulation.getInstance().getAmbulancia().retornoAutomatico();
		ThreadUtil.simulateTime( this.getAverageTaskTime() * 2 );
	}

}
