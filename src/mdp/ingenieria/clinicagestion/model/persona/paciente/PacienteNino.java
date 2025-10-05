package mdp.ingenieria.clinicagestion.model.persona.paciente;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.IProtocoloOcuparSala;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteNino extends Paciente {
    public PacienteNino(String NyA, String dni, Domicilio domicilio, int nroHistoriaClinica) {
        super(NyA, dni, domicilio, nroHistoriaClinica);
    }

    public void ocuparSala() {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();

        if (salaPrivada.estaOcupada()) {
            salaPrivada.getPaciente().ocuparSalaNino(this);
        } else {
            salaPrivada.ocupar(this);
        }
    }

    public void ocuparSalaNino(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }

    public void ocuparSalaJoven(IProtocoloOcuparSala paciente) {
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPatio.ocupar((Paciente) paciente);
    }

    public void ocuparSalaMayor(IProtocoloOcuparSala paciente) {
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();

        salaPrivada.ocupar((Paciente) paciente);
        salaPatio.ocupar(this);
    }
}
