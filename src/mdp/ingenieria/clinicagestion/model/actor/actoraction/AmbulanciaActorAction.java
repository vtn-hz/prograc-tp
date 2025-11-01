package mdp.ingenieria.clinicagestion.model.actor.actoraction;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.actor.ActorAction;

import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class AmbulanciaActorAction extends ActorAction<AmbulanciaMock> {

	public static final int RETORNO_AUTOMATICO = 4;
	
	public AmbulanciaActorAction(int actionTag, int taskTime) {
		super(actionTag, taskTime);
	}

	@Override
	public void execute(AmbulanciaMock actor) {
		ThreadUtil.simulateTime( this.getTaskTime() );
		
		switch(this.getActionTag()) {
			case RETORNO_AUTOMATICO:
				actor.retornoAutomatico();
				break;
		}
	}

}
