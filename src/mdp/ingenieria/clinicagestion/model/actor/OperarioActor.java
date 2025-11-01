package mdp.ingenieria.clinicagestion.model.actor;

import mdp.ingenieria.clinicagestion.model.persona.Operario;

public class OperarioActor extends Thread {

	private Operario operario;
	
	/**
	 * se podria agregar otro parametro para implementar 
	 * el patron observer observable, y que los asociados observen la simulacion,
	 * cuando esta termina les envia un mensaje a todos que la simulacion termino
	 */
	
	public OperarioActor( Operario operario ) { // may add mode()
		assert operario != null;
		this.operario = operario;
	}

	@Override
	public void run() {
		// ...
	}
	
	

}
