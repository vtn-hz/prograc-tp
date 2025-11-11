package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Operario extends Persona {
	
	private Ambulancia ambulancia; // may change when ambulancia implemented
	
	public Operario(String NyA, String dni, Domicilio domicilio, Ambulancia ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}
	
	public Operario(String NyA, String dni, Domicilio domicilio) {
		super(NyA, dni, domicilio);
	}
	
	public void setAmbulancia(Ambulancia ambulancia)
	{
		this.ambulancia = ambulancia;
	}
	
	public void solicitaMantenimiento() {
		this.ambulancia.solicitarMantenimiento(this);
	}
}
