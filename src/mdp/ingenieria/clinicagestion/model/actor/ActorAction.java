package mdp.ingenieria.clinicagestion.model.actor;

public abstract class ActorAction<T> {
		
	private int actionTag;
		
	private int taskTime;
	
	public ActorAction(int actionTag, int taskTime) {
		super();
		this.actionTag = actionTag;
		this.taskTime = taskTime;
	}
	
	public int getActionTag() {
		return this.actionTag;
	}
	
	public int getTaskTime() {
		return this.taskTime;
	}

	public abstract void execute (T actor);
}
