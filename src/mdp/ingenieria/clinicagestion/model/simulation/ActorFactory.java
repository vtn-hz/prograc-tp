package mdp.ingenieria.clinicagestion.model.simulation;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;

import mdp.ingenieria.clinicagestion.model.simulation.actor.AsociadoActor;
import mdp.ingenieria.clinicagestion.model.simulation.actor.OperarioActor;

public class ActorFactory {

	public static final String ASOCIADO_ACTOR = "ASOCIADO_ACTOR";

	public static final String OPERARIO_ACTOR = "OPERARIO_ACTOR";

	public static Actor createTemporallActor(String actorType, int interactionCount, Persona persona) {
		assert actorType != null;
		assert persona != null;

		Actor actor = null;

		switch(actorType)
		{
			case ASOCIADO_ACTOR:
				assert persona instanceof Asociado;
				actor = new AsociadoActor(interactionCount, (Asociado) persona);
				break;

			case OPERARIO_ACTOR:
				assert persona instanceof Operario;
				actor = new OperarioActor(interactionCount, (Operario) persona);
				break;
		}

		return actor;
	}
}