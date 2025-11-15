package mdp.ingenieria.clinicagestion.model.simulation;

public class SimulationWatcherFactory {

	public static final String AMBULANCIA_WATCHER = "AMBULANCIA_WATCHER";

	public static SimulationWatcher create(String watcherType) {
		assert watcherType != null;

		SimulationWatcher watcher = null;

		switch(watcherType)
		{
			case AMBULANCIA_WATCHER:
				watcher = new AmbulanciaSimulationWatcher();
				break;
		}

		return watcher;
	}
}
