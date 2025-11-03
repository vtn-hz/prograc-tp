package mdp.ingenieria.clinicagestion.model.simulation.actoraction;

import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.simulation.ActorAction;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class OperarioActorAction extends ActorAction<Operario> {
	
	public static final int SOLICITA_MANTENIMIENTO = 3;
	
	public OperarioActorAction(int actionTag, int taskTime) {
		super(actionTag, taskTime);
	}

	@Override
	public void execute(Operario actor) {
		ThreadUtil.simulateTime( this.getTaskTime() );
		
		switch(this.getActionTag()) {
			case SOLICITA_MANTENIMIENTO:
				actor.solicitaMantenimiento();
				break;
		}
	}
}