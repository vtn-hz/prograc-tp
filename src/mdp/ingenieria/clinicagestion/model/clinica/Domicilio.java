package mdp.ingenieria.clinicagestion.model.clinica;

public class Domicilio {
    private String telefono;
    private String ciudad;
    private String direccion;

    public Domicilio(String telefono, String ciudad, String direccion) {
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
