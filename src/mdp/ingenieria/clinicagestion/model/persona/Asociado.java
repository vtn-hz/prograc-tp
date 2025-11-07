package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Asociado extends Persona {
	
	private AmbulanciaMock ambulancia; // may change when ambulancia implemented
	
	public Asociado(String NyA, String dni, Domicilio domicilio, AmbulanciaMock ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}
	
	public void solicitaAmbulanciaDomicilio() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarAtencionDomicilio();
	}
	
	public void solicitaAmbulancia() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarTraslado();
	}
}
