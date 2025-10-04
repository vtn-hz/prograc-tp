package sistema.gestion.clinica.modelo;

import sistema.gestion.clinica.Interfaces.IProtocoloOcuparSala;

public class PacienteNino extends Paciente {
    public PacienteNino(String NyA, String dni, Domicilio domicilio, String historiaClinica) {
        super(NyA, dni, domicilio, historiaClinica);
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
