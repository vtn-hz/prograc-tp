package sistema.gestion.clinica.modelo;

import sistema.gestion.clinica.Interfaces.IProtocoloOcuparSala;

public abstract class Paciente extends Persona implements IProtocoloOcuparSala  {

    private String historiaClinica;
    
    public Paciente(String NyA, String dni, Domicilio domicilio, String historiaClinica) {
        super(NyA, dni, domicilio);
        this.historiaClinica = historiaClinica;
    }

    public String getHistoriaClinica() {
        return historiaClinica;
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
                '}';
    }
}