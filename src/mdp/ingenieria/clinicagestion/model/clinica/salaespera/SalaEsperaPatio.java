package mdp.ingenieria.clinicagestion.model.clinica.salaespera;

import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.exception.PacienteNoEncontradoException;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Representa la sala de espera del patio de la clínica.
 * Almacena los pacientes que esperan ser atendidos.
 * Implementa el patrón Singleton para garantizar una única instancia.
 */
public class SalaEsperaPatio {
    private ArrayList<Paciente> pacientes;
    private static SalaEsperaPatio _instancia;

    /**
     * Retorna la única instancia de SalaEsperaPatio.
     * Si aún no existe, la crea.
     *
     * <b>pre:</b> no requiere inicialización previa <br>
     * <b>post:</b> garantiza una única instancia
     *
     * @return instancia única de SalaEsperaPatio
     */
    public static SalaEsperaPatio getInstance() {
        if (_instancia == null) {
            _instancia = new SalaEsperaPatio();
        }
        return _instancia;
    }

    /**
     * Agrega un paciente a la sala de espera del patio.
     *
     * <b>pre:</b> paciente no debe ser nulo <br>
     * <b>post:</b> el paciente queda registrado en la lista de espera
     *
     * @param paciente      paciente que ingresa a la sala de espera
     */
    public void ocupar(Paciente paciente) {
        if (this.pacientes == null) {
            this.pacientes = new ArrayList<>();
        }
        this.pacientes.add(paciente);
    }

    /**
     * Quita un paciente de la sala de espera del patio.
     * Si el paciente no se encuentra en la lista, lanza una excepción.
     *
     * <b>pre:</b> paciente no debe ser nulo; la sala puede contener pacientes <br>
     * <b>post:</b> el paciente se elimina de la lista o se lanza PacienteNoEncontradoException si no estaba presente
     *
     * @param paciente      paciente que debe ser retirado de la sala de espera
     * @throws PacienteNoEncontradoException si el paciente no está registrado en la sala
     */
    public void desocupar(Paciente paciente) throws PacienteNoEncontradoException
    {
        if (this.pacientes != null) {
            if(!this.pacientes.remove(paciente))
                throw new PacienteNoEncontradoException(paciente);
        }
    }

    /**
     * Busca un paciente en la sala de espera según su número de historia clínica.
     *
     * <b>pre:</b> número de historia clínica válido; la lista de pacientes debe estar inicializada <br>
     * <b>post:</b> devuelve el paciente encontrado o null si no existe coincidencia
     *
     * @param nroHistoriaClinica        número de historia clínica del paciente buscado
     * @return el Paciente correspondiente, o null si no está en la sala
     */
    public Paciente getPacienteByHistoriaClinica(int nroHistoriaClinica) {
        if (this.pacientes != null) {
            for (Paciente paciente : this.pacientes) {
                if (paciente.getNroHistoriaClinica() == nroHistoriaClinica) {
                    return paciente;
                }
            }
        }
        return null;
    }
}