package sistema.gestion.clinica.modelo;

public abstract class Persona {
    protected String NyA;
    protected String dni;
    private Domicilio domicilio;
    protected String ciudad;
    protected String telefono;

    public Persona(String NyA, String dni, Domicilio domicilio) {
        this.NyA = NyA;
        this.dni = dni;
        this.domicilio = domicilio;
        this.ciudad = domicilio.getCiudad();
        this.telefono = domicilio.getTelefono();
    }

    public String getNyA() {
        return NyA;
    }

    public String getDni() {
        return dni;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    @Override
    public String toString() {
        return "Persona{" +
                "Nombre y Apellido='" + NyA + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio=" + domicilio +
                ", ciudad='" + ciudad + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    protected abstract String getTipoPersona();
    protected abstract String getInfoEspecialista();
}
