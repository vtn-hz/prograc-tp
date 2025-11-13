package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Asociado extends Persona {
	
	private Ambulancia ambulancia;
	
	public Asociado(String NyA, String dni, Domicilio domicilio, Ambulancia ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}
	
	public void solicitaAmbulanciaDomicilio() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarAtencionDomicilio(this);
	}
	
	public void solicitaAmbulancia() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarTraslado(this);
	}
}
