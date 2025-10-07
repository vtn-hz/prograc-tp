package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;

/**
 * Clase base abstracta para representar a una persona de la clinica.
 */
public abstract class Persona {
    
	private String NyA;
	private String dni;
	private Domicilio domicilio;

    /**
     * Constructor de una persona con los datos provistos.
     *
     * <b>pre:</b> NyA y dni no deben ser nulos ni vacíos; domicilio no debe ser nulo <br>
     * <b>post:</b> se inicializa la instancia con identidad y domicilio
     *
     * @param NyA               nombre y apellido
     * @param dni               documento de identidad
     * @param domicilio         domicilio de la persona
     */
    public Persona(String NyA, String dni, Domicilio domicilio) {
        this.NyA = NyA;
        this.dni = dni;
        this.domicilio = domicilio;

    }

    /**
     * Retorna el nombre y apellido.
     * <b>post:</b> devuelve el nombre y apellido
     *
     * @return nombre y apellido
     */
    public String getNyA() {
        return NyA;
    }

    /**
     * Retorna el documento nacional de identidad.
     * <b>post:</b> devuelve el DNI
     *
     * @return DNI de la persona
     */
    public String getDni() {
        return dni;
    }

    /**
     * Retorna el domicilio asociado.
     * <b>post:</b> devuelve el domicilio
     *
     * @return objeto {@link Domicilio} de la persona
     */
    public Domicilio getDomicilio() {
        return domicilio;
    }

    /**
     * Devuelve una representación textual de la persona.
     * <b>post:</b> retorna una cadena descriptiva con los campos principales
     *
     * @return String con los datos de la persona
     */
    @Override
    public String toString() {
        return "Persona{" +
                "Nombre y Apellido='" + NyA + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio=" + domicilio +
                '}';
    }
}
