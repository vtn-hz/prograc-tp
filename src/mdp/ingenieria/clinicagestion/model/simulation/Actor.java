package mdp.ingenieria.clinicagestion.model.simulation;

public abstract class Actor extends Thread {
	
	public static final int PENDING = 0;
	
	public static final int RUNNING = 1;
	
	public static final int TERMINATED = 2;

    /**
     * Constructor por defecto.
     *
     * <b>post:</b> se crea el actor sin iniciar su ejecución
     */
	public Actor() {
	}

    /**
     * Metodo principal del hilo del actor.
     * Debe ser implementado por cada tipo concreto de actor.
     */
	@Override
	public abstract void run() ;

    /**
     * Ejecuta una unidad de trabajo del actor dentro de la simulación.
     * Debe ser definida por las clases hijas.
     */
	public abstract void runTask() ;

    /**
     * Devuelve el tiempo promedio configurado para las tareas del actor.
     *
     * @return tiempo medio de ejecución de una tarea
     */
	protected int getAverageTaskTime() {
		return Simulation.getInstance().getTaskTime();
	}
}
