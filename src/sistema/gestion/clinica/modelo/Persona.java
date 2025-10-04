package sistema.gestion.clinica.modelo;

public abstract class Persona {
    protected String NyA;
    protected String dni;
    private Domicilio domicilio;

    public Persona(String NyA, String dni, Domicilio domicilio) {
        this.NyA = NyA;
        this.dni = dni;
        this.domicilio = domicilio;

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
    
    @Override
    public String toString() {
        return "Persona{" +
                "Nombre y Apellido='" + NyA + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio=" + domicilio +
                '}';
    }

    protected abstract String getTipoPersona();
    protected abstract String getInfoEspecialista();
}
