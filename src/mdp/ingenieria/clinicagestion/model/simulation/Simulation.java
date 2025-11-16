package mdp.ingenieria.clinicagestion.model.simulation;

import java.util.ArrayList;
import java.util.Observable;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.persona.Persona;

/**
 * Tiene como invariante de clase que ningun actor de la simulacion puede ejecutarse sin ser registrado por el temporalThreadWorkingCount.
 * Las únicas entidades autorizadas para cambiar el estado de la simulacion a TERMINATED son los simulationWatcher o la misma Simulacion
 */

public class Simulation extends Observable {

	public static final String STATE_RUNNING = "running";

	public static final String STATE_FINALIZING = "finalizing";

	public static final String STATE_TERMINATED =  "terminated";

	private static Simulation _instance;

	private volatile String status;

	private Ambulancia ambulancia;

	private int taskTime;

	private int temporalThreadWorkingCount;

	private Simulation () {
		this.status = Simulation.STATE_TERMINATED;
		this.temporalThreadWorkingCount = 0;
		this.taskTime = ThreadUtil.MEDIUM;
		this.ambulancia = new Ambulancia();
	}

    /**
     * Devuelve la instancia única de Simulation.
     *
     * <b>pre:</b> ninguna <br>
     * <b>post:</b> siempre devuelve la misma instancia
     *
     * @return instancia Singleton de Simulation
     */
	public static synchronized Simulation getInstance()
	{
		if (_instance == null)
			_instance = new Simulation();

		return _instance;
	}

    /**
     * Actualiza el estado de la simulación y notifica a los observadores.
     *
     * <b>pre: </b> solo se deberian utilizar estados que existan <br>
     * <b>post:</b> el estado interno se actualiza y se envía un SimulationStateMessage
     *
     * @param status nuevo estado de la simulación
     */
	public synchronized void  setStatus ( String status ) {
		this.status = status;
		setChanged();
		notifyObservers(new SimulationStateMessage(this.status));
	}

    /**
     * Indica si la simulación está en ejecución.
     *
     * @return true si el estado es STATE_RUNNING
     */
	public boolean isRunning() {
		return this.status == Simulation.STATE_RUNNING;
	}

    /**
     * Indica si la simulación se encuentra en proceso de finalización.
     *
     * @return true si el estado es STATE_FINALIZING
     */
	public boolean isFinalizing() {
		return this.status == Simulation.STATE_FINALIZING;
	}

    /**
     * Indica si la simulación ha finalizado.
     *
     * <b>pre:</b> la unica entidad que deberia llamar a terminated es un SimulationWatcher, <br>
     * en este caso seria la AmbulanciaSimulationWatcher <br>
     * <b>post:</b> no modifica el estado
     *
     * @return true si el estado es STATE_TERMINATED
     */
	public boolean isTerminated() {
		return this.status == Simulation.STATE_TERMINATED;
	}

    /**
     * Establece directamente la cantidad de hilos temporales activos.
     *
     * @param count nuevo contador de hilos temporales
     */
	public void setThreadWorkingCount( int count )
	{
		this.temporalThreadWorkingCount = count;
	}

    /**
     * Indica si aún quedan hilos temporales en ejecución.
     *
     * @return true si temporalThreadWorkingCount es mayor a 0
     */
	public synchronized boolean hasTemporalThreadWorking()
	{
		return this.temporalThreadWorkingCount > 0;
	}

    /**
     * Indica que un hilo temporal ha finalizado su trabajo.
     * Decrementa el contador de hilos activos.
     */
	public synchronized void temporalThreadFinalized()
	{
		this.temporalThreadWorkingCount--;
	}

    /**
     * Indica que un hilo temporal ha finalizado y notifica a los observadores
     * incluyendo el modelo Persona asociado.
     *
     * @param persona persona cuyo actor terminó su ejecución
     */
	public synchronized void temporalThreadFinalized(Persona persona)
	{
		this.temporalThreadWorkingCount--;
		setChanged();
		notifyObservers(new SimulationStateMessage(this.status, persona));
	}

    /**
     * Indica que un hilo temporal ha comenzado su ejecución.
     * Incrementa el contador de hilos activos.
     */
	public synchronized void temporalThreadStarted()
	{
		this.temporalThreadWorkingCount++;
	}

    /**
     * Devuelve la ambulancia utilizada en la simulación.
     *
     * @return ambulancia actual
     */
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

    /**
     * Reemplaza la ambulancia utilizada en la simulación.
     *
     * @param ambulancia nueva ambulancia
     */
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

    /**
     * Devuelve el tiempo promedio configurado para las tareas.
     *
     * @return tiempo de tarea en milisegundos (o unidad definida)
     */
	public int getTaskTime() {
		return taskTime;
	}

    /**
     * Configura el tiempo promedio de las tareas de los actores.
     *
     * @param taskTime nuevo tiempo de tarea
     */
	public void setTaskTime(int taskTime) {
		this.taskTime = taskTime;
	}

    /**
     * Inicia la simulación creando actores para los asociados y el operario,
     * además del watcher de ambulancia. Luego comienza sus hilos y establece
     * el estado de la simulación en STATE_RUNNING.
     *
     * <b>pre:</b> asociadosDto y operarioDto no deben ser nulos;
     * interactionCount ≥ 0 <br>
     * <b>post:</b> se crean y arrancan los actores y el watcher, y la simulación
     * queda en estado running
     *
     * @param asociadosDto arreglo de asociados que participarán en la simulación
     * @param operarioDto  datos del operario de mantenimiento
     * @param interactionCount cantidad de interacciones por actor
     */
	public void start(AsociadoDTO[] asociadosDto, PersonaDTO operarioDto, int interactionCount)
	{
	    ArrayList<Actor> asociadosActores = new ArrayList<>();
	    Actor operarioActor = null;
	    SimulationWatcher ambulanciaWatcher = null;

	    for (PersonaDTO dto : asociadosDto) {
	        Persona asociado = AmbulanciaInteractorFactory.create(
	            AmbulanciaInteractorFactory.ASOCIADO,
	            dto,
	            this.ambulancia
	        );

	        Actor actor = ActorFactory.createTemporallActor(
	            ActorFactory.ASOCIADO_ACTOR,
	            interactionCount,
	            asociado
	        );

	        asociadosActores.add(actor);
	    }

	    Persona operario = AmbulanciaInteractorFactory.create(
	        AmbulanciaInteractorFactory.OPERARIO,
	        operarioDto,
	        this.ambulancia
	    );

	    operarioActor = ActorFactory.createTemporallActor(
	        ActorFactory.OPERARIO_ACTOR,
            interactionCount,
            operario
	    );

	    ambulanciaWatcher = SimulationWatcherFactory.create(
	        SimulationWatcherFactory.AMBULANCIA_WATCHER
	    );

	    this.setThreadWorkingCount(asociadosActores.size() + 1);
	    this.setStatus( Simulation.STATE_RUNNING );

	    ambulanciaWatcher.start();
	    operarioActor.start();
	    asociadosActores.forEach(Actor::start);
	}


}
