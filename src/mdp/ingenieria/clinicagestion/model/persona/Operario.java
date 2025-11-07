package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Operario extends Persona {
	
	private AmbulanciaMock ambulancia; // may change when ambulancia implemented
	
	public Operario(String NyA, String dni, Domicilio domicilio, AmbulanciaMock ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}
	
	public void solicitaMantenimiento() {
		this.ambulancia.solicitarMantenimiento();
	}
}
