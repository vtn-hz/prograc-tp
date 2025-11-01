package mdp.ingenieria.clinicagestion.model.actor;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;

public class AsociadoActor extends Thread {

	private Asociado asociado;
	
	/**
	 * se podria agregar otro parametro para implementar 
	 * el patron observer observable, y que los asociados observen la simulacion,
	 * cuando esta termina les envia un mensaje a todos que la simulacion termino
	 */
	
	public AsociadoActor( Asociado asocidado ) { // may add mode()
		assert asociado != null;
		this.asociado = asociado;
	}

	@Override
	public void run() {
		// ...
	}
	
	

}
