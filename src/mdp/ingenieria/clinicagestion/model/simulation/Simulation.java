package mdp.ingenieria.clinicagestion.model.simulation;

import java.util.ArrayList;
import java.util.Observable;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.persona.Persona;

public class Simulation extends Observable {

	public static final String STATE_RUNNING = "running";

	public static final String STATE_FINALIZING = "finalizing";

	public static final String STATE_TERMINATED =  "terminated";

	private static Simulation _instance;

	private String status;

	private Ambulancia ambulancia;

	private int taskTime;

	private int temporalThreadWorkingCount;

	private Simulation () {
		this.status = Simulation.STATE_TERMINATED;
		this.temporalThreadWorkingCount = 0;
		this.taskTime = 2000;
		this.ambulancia = new Ambulancia();
	}

	public static Simulation getInstance()
	{
		if (_instance == null)
			_instance = new Simulation();

		return _instance;
	}

	/**
	 * <b>pre: </b> solo se deberian utilizar estados que existan
	 * @param status
	 */
	public synchronized void  setStatus ( String status ) {
		this.status = status;
		setChanged();
		notifyObservers(new SimulationStateMessage(this.status));
	}

	public boolean isRunning() {
		return this.status == Simulation.STATE_RUNNING;
	}

	public boolean isFinalizing() {
		return this.status == Simulation.STATE_FINALIZING;
	}

	/**
	 * <b>pre:</b> la unica entidad que deberia llamar a terminated es un SimulationWatcher, <br>
	 * en este caso seria la AmbulanciaSimulationWatcher
	 * @return boolean
	 */
	public boolean isTerminated() {
		return this.status == Simulation.STATE_TERMINATED;
	}

	public void setThreadWorkingCount( int count )
	{
		this.temporalThreadWorkingCount = count;
	}

	public synchronized boolean hasTemporalThreadWorking()
	{
		return this.temporalThreadWorkingCount > 0;
	}

	public synchronized void temporalThreadFinalized()
	{
		this.temporalThreadWorkingCount--;
	}
	
	public synchronized void temporalThreadFinalized(Persona persona)
	{
		this.temporalThreadWorkingCount--;
		setChanged();
		notifyObservers(new SimulationStateMessage(this.status, persona));
	}

	public synchronized void temporalThreadStarted()
	{
		this.temporalThreadWorkingCount++;
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public int getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(int taskTime) {
		this.taskTime = taskTime;
	}

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
