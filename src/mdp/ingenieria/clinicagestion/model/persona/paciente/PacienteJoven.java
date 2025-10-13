package mdp.ingenieria.clinicagestion.model.persona.paciente;

import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.IProtocoloOcuparSala;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

/**
 * Representa a un paciente joven y define el protocolo de ocupación de salas de espera.
 */
public class PacienteJoven extends Paciente {
    /**
     * Constructor de un paciente joven con los datos provistos.
     *
     * <b>pre:</b> NyA, dni y domicilio no deben ser nulos ni vacíos; el número de historia clínica debe ser válido (no negativo) <br>
     * <b>post:</b> se inicializa la instancia de paciente joven
     *
     * @param NyA                       nombre y apellido
     * @param dni                       documento de identidad
     * @param domicilio                 domicilio del paciente
     * @param nroHistoriaClinica        número de historia clínica
     */
    public PacienteJoven(String NyA, String dni, Domicilio domicilio, int nroHistoriaClinica) {
        super(NyA, dni, domicilio, nroHistoriaClinica);
    }

    /**
     * Ocupa una sala de espera según disponibilidad:
     * si la sala privada está libre, la ocupa; de lo contrario, delega en el
     * paciente que la ocupa para que reubique a este paciente joven.
     *
     * <b>pre:</b> existe una instancia válida de SalaEsperaPrivada <br>
     * <b>post:</b> el paciente queda ubicado en la sala privada o se delega la resolución
     */
    public void ocuparSala() {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();

        if (salaPrivada.estaOcupada()) {
            salaPrivada.getPaciente().ocuparSalaJoven(this);
        } else {
            salaPrivada.ocupar(this);
        }
    }

    /**
     * Regla de interacción entre Niño y Joven:
     * el niño ocupa SalaEsperaPrivada y el joven ocupa SalaEsperaPatio.
     *
     * <b>pre:</b> paciente implementa IProtocoloOcuparSala y es instancia de Paciente <br>
     * <b>post:</b> el niño queda en sala privada y el joven en patio
     *
     * @param paciente      paciente niño con quien se resuelve la ubicación
     * @throws ClassCastException si paciente no es de tipo Paciente
     */
    public void ocuparSalaNino(IProtocoloOcuparSala paciente) {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPrivada.ocupar((Paciente) paciente);
        salaPatio.ocupar(this);
    }

    /**
     * Regla de interacción entre Joven y Joven:
     * el nuevo pacientees ubicado en SalaEsperaPatio.
     *
     * <b>pre:</b> paciente implementa IProtocoloOcuparSala y es instancia de Paciente <br>
     * <b>post:</b> el paciente recibido queda en patio
     *
     * @param paciente      otro paciente joven a reubicar
     * @throws ClassCastException si paciente no es de tipo Paciente
     */
    public void ocuparSalaJoven(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }

    /**
     * Regla de interacción entre Mayor y Joven:
     * el paciente recibido (mayor) es ubicado en SalaEsperaPatio.
     *
     * <b>pre:</b> paciente implementa IProtocoloOcuparSala y es instancia de Paciente <br>
     * <b>post:</b> el paciente recibido queda en patio
     *
     * @param paciente      paciente mayor a reubicar
     * @throws ClassCastException si paciente no es de tipo Paciente
     */
    public void ocuparSalaMayor(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }
}
