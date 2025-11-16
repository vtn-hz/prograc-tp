package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public class Operario extends Persona {
	
	private Ambulancia ambulancia;

    /**
     * Crea un operario con ambulancia asignada.
     *
     * @param NyA nombre y apellido del operario
     * @param dni documento del operario
     * @param domicilio domicilio del operario
     * @param ambulancia ambulancia asignada
     */
	public Operario(String NyA, String dni, Domicilio domicilio, Ambulancia ambulancia) {
		super(NyA, dni, domicilio);
		this.ambulancia = ambulancia;
	}

    /**
     * Crea un operario sin ambulancia asignada inicialmente.
     *
     * @param NyA nombre y apellido
     * @param dni documento
     * @param domicilio domicilio
     */
	public Operario(String NyA, String dni, Domicilio domicilio) {
		super(NyA, dni, domicilio);
	}

    /**
     * Asigna una ambulancia al operario.
     *
     * @param ambulancia ambulancia a asignar
     */
	public void setAmbulancia(Ambulancia ambulancia)
	{
		this.ambulancia = ambulancia;
	}

    /**
     * Solicita mantenimiento para la ambulancia asignada.
     *
     * <b>pre:</b> la ambulancia no debe ser nula <br>
     * <b>post:</b> se envía la solicitud de mantenimiento
     */
	public void solicitaMantenimiento() {
		this.ambulancia.solicitarMantenimiento(this);
	}
}
