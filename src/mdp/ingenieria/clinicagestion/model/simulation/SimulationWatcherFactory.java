package mdp.ingenieria.clinicagestion.model.simulation;

public class SimulationWatcherFactory {

	public static final String AMBULANCIA_WATCHER = "AMBULANCIA_WATCHER";

    /**
     * Crea un SimulationWatcher según el tipo solicitado.
     *
     * <b>pre:</b> watcherType no debe ser nulo y debe corresponder a un tipo válido <br>
     * <b>post:</b> se devuelve un watcher configurado para la simulación
     *
     * @param watcherType tipo de watcher a crear
     * @return instancia correspondiente del watcher, o null si el tipo no coincide
     */
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
