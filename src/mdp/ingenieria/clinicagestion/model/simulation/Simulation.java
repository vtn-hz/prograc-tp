package mdp.ingenieria.clinicagestion.model.simulation;



import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.data.ActorDTO;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class Simulation {
	
	private static Simulation _instance;
	
	private boolean isRunning;
	
	private int temporalActorsWorkingCount;
	
	private Simulation () {
		this.isRunning = false;
		this.temporalActorsWorkingCount = 0;
	}
	
	public static Simulation getInstance() 
	{
		if (_instance == null)
			_instance = new Simulation();
		
		return _instance;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void setTemporalActorWorkingCount( int count )
	{
		this.temporalActorsWorkingCount = count;
	}
	
	public synchronized boolean hasTemporalActorsWorking()
	{
		return this.temporalActorsWorkingCount > 0;
	}
	
	public synchronized void temporalActorFinalize() 
	{
		this.temporalActorsWorkingCount--;
	}
	
	public void start(ActorDTO[] asociadosDto, ActorDTO operarioDto) {
	    ArrayList<Actor> asociadosActores = new ArrayList<>();
	    Actor operarioActor = null;
	    Actor ambulanciaActor = null;

	  
	    AmbulanciaMock ambulancia = new AmbulanciaMock();

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

	        // Crear el actor asociado
	        Actor actor = ActorFactory.createTemporallActor(
	            ActorFactory.ASOCIADO_ACTOR,
	            ThreadUtil.MEDIUM,
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
            ThreadUtil.MEDIUM,
            operarioDto.getInteractionCount(),
            operario
	    );

	    // Crear la ambulancia persistente
	    ambulanciaActor = ActorFactory.createPersistentActor(
	        ActorFactory.AMBULANCIA_ACTOR,
	        ThreadUtil.MEDIUM,
	        ambulancia
	    );

	    this.setTemporalActorWorkingCount(asociadosActores.size() + 1);
	    this.setRunning(true);

	    ambulanciaActor.start();
	    operarioActor.start();
	    asociadosActores.forEach(Actor::start);
	}
}
