package mdp.ingenieria.clinicagestion.persistence;

public class PersonaDTO {
    private String dni;
    private String nya;
    private String telefono;
    private String ciudad;
    private String direccion;

    public PersonaDTO(String dni, String nya, String telefono, String ciudad, String direccion) {
        this.dni = dni;
        this.nya = nya;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getDni() { return dni; }
    public String getNya() { return nya; }
    public String getNyA() { return nya; }
    public String getTelefono() { return telefono; }
    public String getCiudad() { return ciudad; }
    public String getDireccion() { return direccion; }
}