package mdp.ingenieria.clinicagestion.model.simulation;

import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.data.ActorDTO;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class Simulation {
	
	public static final String STATE_RUNNING = "running";
	
	public static final String STATE_FINALIZING = "finalizing";
	
	public static final String STATE_TERMINATED =  "terminated";
	
	private static Simulation _instance;
	
	private String status; 
	
	private int temporalThreadWorkingCount;
	
	private Simulation () {
		this.status = Simulation.STATE_TERMINATED;
		this.temporalThreadWorkingCount = 0;
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
	public void setStatus ( String status ) {
		this.status = status;
	}
	
	public boolean isRunning() {
		return this.status == Simulation.STATE_RUNNING;
	}
	
	public boolean isFinalizing() {
		return this.status == Simulation.STATE_FINALIZING;
	}

	/**
	 * <b>pre:</b> la unica entidad que deberia llamar a terminated es un persistentthread, <br>
	 * en este caso seria la ambulancia
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
	
	public synchronized void temporalThreadStarted() 
	{
		this.temporalThreadWorkingCount++;
	}
	
	public void start(ActorDTO[] asociadosDto, ActorDTO operarioDto, AmbulanciaMock ambulancia, int taskTime)
	{
	    ArrayList<Actor> asociadosActores = new ArrayList<>();
	    Actor operarioActor = null;
	    Actor ambulanciaActor = null;

	    for (ActorDTO dto : asociadosDto) {
	        Persona asociado = AmbulanciaInteractorFactory.create(
	            AmbulanciaInteractorFactory.ASOCIADO,
	            dto.getNyA(),
	            dto.getDni(),
	            dto.getTelefono(),
	            dto.getCiudad(),
	            dto.getDireccion(),
	            ambulancia
	        );
	        
	        Actor actor = ActorFactory.createTemporallActor(
	            ActorFactory.ASOCIADO_ACTOR,
	            taskTime,
	            dto.getInteractionCount(),
	            asociado
	        );

	        asociadosActores.add(actor);
	    }

	    Persona operario = AmbulanciaInteractorFactory.create(
	        AmbulanciaInteractorFactory.OPERARIO,
	        operarioDto.getNyA(),
	        operarioDto.getDni(),
	        operarioDto.getTelefono(),
	        operarioDto.getCiudad(),
	        operarioDto.getDireccion(),
	        ambulancia
	    );

	    operarioActor = ActorFactory.createTemporallActor(
	        ActorFactory.OPERARIO_ACTOR,
	        taskTime,
            operarioDto.getInteractionCount(),
            operario
	    );

	    ambulanciaActor = ActorFactory.createPersistentActor(
	        ActorFactory.AMBULANCIA_ACTOR,
	        taskTime,
	        ambulancia
	    );

	    this.setThreadWorkingCount(asociadosActores.size() + 1);
	    this.setStatus( Simulation.STATE_RUNNING );

	    ambulanciaActor.start();
	    operarioActor.start();
	    asociadosActores.forEach(Actor::start);
	}
	
	public void start(ActorDTO[] asociadosDto, ActorDTO operarioDto, AmbulanciaMock ambulancia)
	{
		this.start(asociadosDto, operarioDto, ambulancia, ThreadUtil.MEDIUM);
	}
	
	
}
