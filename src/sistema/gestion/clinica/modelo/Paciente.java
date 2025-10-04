package sistema.gestion.clinica.modelo;

import java.time.LocalDateTime;

public class Paciente extends Persona {

    private String historiaClinica;
    private int numeroOrden;
    private LocalDateTime fechaIngreso;

    public Paciente(String NyA, String dni, Domicilio domicilio, String historiaClinica) {
        super(NyA, dni, domicilio);
        this.historiaClinica = historiaClinica;
        this.fechaIngreso = LocalDateTime.now();
    }

    public String getHistoriaClinica() {
        return historiaClinica;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getRangoEtario() {
        // TODO falta implementación
        return 0;
    }

    @Override
    protected String getTipoPersona() {
        return "Paciente";
    }

    @Override
    protected String getInfoEspecialista() {
        return "Historia Clínica: " + historiaClinica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "Nombre y Apellido='" + getNyA() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", direccion=" + getDomicilio().getDireccion() +
                ", ciudad='" + getDomicilio().getCiudad() + '\'' +
                ", telefono='" + getDomicilio().getTelefono() + '\'' +
                ", historiaClinica='" + historiaClinica + '\'' +
                ", numeroOrden=" + numeroOrden +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}