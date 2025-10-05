package mdp.ingenieria.clinicagestion.model.clinica.salaespera;

import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.exception.PacienteNoEncontradoException;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class SalaEsperaPatio {
    private ArrayList<Paciente> pacientes;
    private static SalaEsperaPatio _instancia;

    public static SalaEsperaPatio getInstance() {
        if (_instancia == null) {
            _instancia = new SalaEsperaPatio();
        }
        return _instancia;
    }

    public void ocupar(Paciente paciente) {
        if (this.pacientes == null) {
            this.pacientes = new ArrayList<>();
        }
        this.pacientes.add(paciente);
    }

    public void desocupar(Paciente paciente) throws PacienteNoEncontradoException
    {
        if (this.pacientes != null) {
            if(!this.pacientes.remove(paciente))
                throw new PacienteNoEncontradoException(paciente);
        }
    }

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