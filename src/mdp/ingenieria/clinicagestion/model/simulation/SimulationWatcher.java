package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class SimulationWatcher extends Thread {

    /**
     * Constructor por defecto.
     *
     * <b>post:</b> inicializa el watcher sin iniciar su ejecución
     */
	public SimulationWatcher() {
	}

    /**
     * Método principal del hilo del watcher.
     * Cada implementación define la lógica de vigilancia específica.
     */
	@Override
	public abstract void run();

    /**
     * Ejecuta una unidad de trabajo del watcher.
     * Implementada por cada subclase según su comportamiento.
     */
	public abstract void runTask();

    /**
     * Devuelve el tiempo promedio de tareas según la configuración de la simulación.
     *
     * @return tiempo medio de ejecución en milisegundos (o unidad definida)
     */
	protected int getAverageTaskTime() {
		return Simulation.getInstance().getTaskTime();
	}
}
