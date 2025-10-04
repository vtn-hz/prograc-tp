package sistema.gestion.clinica.modelo;

import sistema.gestion.clinica.Interfaces.IProtocoloOcuparSala;

public class PacienteJoven extends Paciente {
    public PacienteJoven(String NyA, String dni, Domicilio domicilio, String historiaClinica) {
        super(NyA, dni, domicilio, historiaClinica);
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
