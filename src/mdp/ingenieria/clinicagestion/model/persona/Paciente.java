package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;

import java.util.Objects;

/**
 * Clase base abstracta para pacientes de la clinica
 */
public abstract class Paciente extends Persona implements IProtocoloOcuparSala  {

    private int nroHistoriaClinica;

    /**
     * Constructor de un paciente con los datos provistos.
     *
     * <b>pre:</b> NyA, dni y domicilio no deben ser nulos ni vacíos; historiaClinica debe ser un identificador válido (≥ 0) <br>
     * <b>post:</b> se inicializa la instancia con identidad personal y número de historia clínica
     *
     * @param NyA                   nombre y apellido
     * @param dni                   documento de identidad
     * @param domicilio             domicilio del paciente
     * @param historiaClinica       número de historia clínica
     */
    public Paciente(String NyA, String dni, Domicilio domicilio, int historiaClinica) {
        super(NyA, dni, domicilio);
        this.nroHistoriaClinica = historiaClinica;
    }

    /**
     * Retorna el número de historia clínica del paciente.
     * <b>post:</b> devuelve el identificador de historia clínica asociado
     *
     * @return número de historia clínica
     */
    public int getNroHistoriaClinica() {
        return nroHistoriaClinica;
    }

    /**
     * Verifica si un paciente es el buscado por su numero de historia clinia
     * @param o  la referencia al objeto con el cual comparar.
     * @return true si el numero de historia clinica es el mismo, o si es el mismo objeto. False en cualquier otro caso.
     */

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if (!(o instanceof Paciente))
            return false;
        Paciente paciente = (Paciente) o;
        return nroHistoriaClinica == paciente.nroHistoriaClinica;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nroHistoriaClinica);
    }

    /**
     * Devuelve una representación textual del paciente.
     * <b>post:</b> retorna una cadena descriptiva con los campos principales
     *
     * @return String con los datos del paciente
     */

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
