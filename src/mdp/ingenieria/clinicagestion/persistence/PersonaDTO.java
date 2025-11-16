package mdp.ingenieria.clinicagestion.persistence;

public class PersonaDTO {
    private String dni;
    private String nya;
    private String telefono;
    private String ciudad;
    private String direccion;

    /**
     * Construye un objeto PersonaDTO con los datos esenciales.
     *
     * <b>pre:</b> todos los parámetros deben ser no nulos ni vacíos <br>
     * <b>post:</b> se inicializa el DTO con los valores dados
     *
     * @param dni documento de identidad
     * @param nya nombre y apellido
     * @param telefono teléfono de contacto
     * @param ciudad ciudad de residencia
     * @param direccion dirección de residencia
     */
    public PersonaDTO(String dni, String nya, String telefono, String ciudad, String direccion) {
        this.dni = dni;
        this.nya = nya;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    /** @return DNI de la persona */
    public String getDni() { return dni; }

    /** @return nombre y apellido */
    public String getNya() { return nya; }

    /** Alias de getNya(), mantenido por compatibilidad. */
    public String getNyA() { return nya; }

    /** @return teléfono de contacto */
    public String getTelefono() { return telefono; }

    /** @return ciudad de residencia */
    public String getCiudad() { return ciudad; }

    /** @return dirección de la persona */
    public String getDireccion() { return direccion; }
}