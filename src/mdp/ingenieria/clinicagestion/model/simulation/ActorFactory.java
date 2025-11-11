package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;

import mdp.ingenieria.clinicagestion.model.simulation.actor.AmbulanciaActor;
import mdp.ingenieria.clinicagestion.model.simulation.actor.AsociadoActor;
import mdp.ingenieria.clinicagestion.model.simulation.actor.OperarioActor;

public class ActorFactory {

	public static final String ASOCIADO_ACTOR = "ASOCIADO_ACTOR";

	public static final String OPERARIO_ACTOR = "OPERARIO_ACTOR";

	public static final String AMBULANCIA_ACTOR = "AMBULANCIA_ACTOR";

	public static Actor createTemporallActor(String actorType, int averageTaskTime, int interactionCount, Persona persona) {
		assert actorType != null;
		assert persona != null;

		Actor actor = null;

		switch(actorType)
		{
			case ASOCIADO_ACTOR:
				assert persona instanceof Asociado;
				actor = new AsociadoActor(averageTaskTime, interactionCount, (Asociado) persona);
				break;

			case OPERARIO_ACTOR:
				assert persona instanceof Operario;
				actor = new OperarioActor(averageTaskTime, interactionCount, (Operario) persona);
				break;
		}

		return actor;
	}

	public static Actor createPersistentActor(String actorType, int averageTaskTime, Object wrapped) {
		assert actorType != null;
		assert wrapped != null;

		Actor actor = null;

		switch(actorType)
		{
			case AMBULANCIA_ACTOR:
				assert wrapped instanceof Ambulancia;
				actor = new AmbulanciaActor(averageTaskTime, (Ambulancia) wrapped);
				break;
		}

		return actor;
	}
}