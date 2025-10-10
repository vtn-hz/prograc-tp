package mdp.ingenieria.clinicagestion.model.clinica;

/**
 * Representa la información de domicilio asociada a una clínica o paciente.
 * Contiene los datos básicos de contacto y ubicación.
 */
public class Domicilio {
	
    private String telefono;
    
    private String ciudad;
    
    private String direccion;

    /**
     * Crea una instancia de Domicilio con los datos especificados.
     *
     * <b>pre:</b> telefono, ciudad y direccion no deben ser nulos ni vacíos <br>
     * <b>post:</b> inicializa el domicilio con los valores proporcionados
     *
     * @param telefono          número de teléfono asociado al domicilio
     * @param ciudad            ciudad donde se ubica el domicilio
     * @param direccion         dirección física completa
     */
    public Domicilio(String telefono, String ciudad, String direccion)
    {
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    /**
     * Retorna el número de teléfono del domicilio.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el número de teléfono asociado
     *
     * @return número de teléfono del domicilio
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Retorna la ciudad del domicilio.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve la ciudad establecida
     *
     * @return ciudad del domicilio
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Retorna la dirección del domicilio.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve la dirección completa del domicilio
     *
     * @return dirección física del domicilio
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve una representación en texto del objeto Domicilio.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve una cadena con los datos de contacto y ubicación
     *
     * @return descripción textual del domicilio
     */
    @Override
    public String toString() {
        return "Domicilio{" +
                "telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
