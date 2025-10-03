package sistema.gestion.clinica.modelo;

public class Clinica {
    private Clinica _instancia;
    private Domicilio domicilio;
    private String nombre;

    private Clinica(Domicilio domicilio, String nombre) {
        this.domicilio = domicilio;
        this.nombre = nombre;
    }

    public Clinica getInstancia(Domicilio domicilio, String nombre) {
        if (_instancia == null) {
            _instancia = new Clinica(domicilio, nombre);
        }
        return _instancia;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Clinica{" +
                "domicilio=" + domicilio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
