package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Asociado extends Persona {
	
	private Ambulancia ambulancia;

    /**
     * Crea un asociado con sus datos personales y una ambulancia vinculada.
     *
     * <b>pre:</b> NyA, dni, domicilio y ambulancia no deben ser nulos <br>
     * <b>post:</b> el asociado queda listo para realizar solicitudes a la ambulancia
     *
     * @param NyA        nombre y apellido del asociado
     * @param dni        documento del asociado
     * @param domicilio  domicilio del asociado
     * @param ambulancia ambulancia asociada
     */
	public Asociado(String NyA, String dni, Domicilio domicilio, Ambulancia ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}

    /**
     * Solicita una atención domiciliaria a la ambulancia.
     *
     * <b>pre:</b> la ambulancia no debe ser nula <br>
     * <b>post:</b> se envía la solicitud a la ambulancia
     */
	public void solicitaAmbulanciaDomicilio() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarAtencionDomicilio(this);
	}

    /**
     * Solicita un traslado en ambulancia.
     *
     * <b>pre:</b> la ambulancia no debe ser nula <br>
     * <b>post:</b> se envía la solicitud a la ambulancia
     */
	public void solicitaAmbulancia() {
		assert this.ambulancia != null ; 
		this.ambulancia.solicitarTraslado(this);
	}
}
