package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;

public abstract class Paciente extends Persona implements IProtocoloOcuparSala  {

    private int nroHistoriaClinica;
    
    public Paciente(String NyA, String dni, Domicilio domicilio, int historiaClinica) {
        super(NyA, dni, domicilio);
        this.nroHistoriaClinica = historiaClinica;
    }

    public int getNroHistoriaClinica() {
        return nroHistoriaClinica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "Nombre y Apellido='" + getNyA() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", direccion=" + getDomicilio().getDireccion() +
                ", ciudad='" + getDomicilio().getCiudad() + '\'' +
                ", telefono='" + getDomicilio().getTelefono() + '\'' +
                ", historiaClinica='" + nroHistoriaClinica + '\'' +
                '}';
    }
}
