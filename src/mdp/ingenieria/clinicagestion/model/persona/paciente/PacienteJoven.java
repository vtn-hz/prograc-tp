package mdp.ingenieria.clinicagestion.model.persona.paciente;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.IProtocoloOcuparSala;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteJoven extends Paciente {
    public PacienteJoven(String NyA, String dni, Domicilio domicilio, int nroHistoriaClinica) {
        super(NyA, dni, domicilio, nroHistoriaClinica);
    }

    public void ocuparSala() {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();

        if (salaPrivada.estaOcupada()) {
            salaPrivada.getPaciente().ocuparSalaJoven(this);
        } else {
            salaPrivada.ocupar(this);
        }
    }

    public void ocuparSalaNino(IProtocoloOcuparSala paciente) {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPrivada.ocupar((Paciente) paciente);
        salaPatio.ocupar(this);
    }

    public void ocuparSalaJoven(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }

    public void ocuparSalaMayor(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }
}
