package mdp.ingenieria.clinicagestion.model.simulation.actoraction;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.simulation.ActorAction;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class AsociadoActorAction extends ActorAction<Asociado> {

	public static final int SOLICITA_AMBULANCIA_DOMICILIO = 0;
	
	public static final int SOLICITA_AMBULANCIA = 1;
	
	public AsociadoActorAction(int actionTag, int taskTime) {
		super(actionTag, taskTime);
	}

	@Override
	public void execute(Asociado actor) {
		ThreadUtil.simulateTime( this.getTaskTime() );
		
		switch(this.getActionTag()) {
			case SOLICITA_AMBULANCIA_DOMICILIO:
				actor.solicitaAmbulanciaDomicilio();
				break;
			case SOLICITA_AMBULANCIA:
				actor.solicitaAmbulancia();
				break;
		}
	}
}