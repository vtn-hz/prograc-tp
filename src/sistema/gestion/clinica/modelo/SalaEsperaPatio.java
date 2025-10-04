package sistema.gestion.clinica.modelo;

import java.util.ArrayList;

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

    public void desocupar(Paciente paciente) {
        if (this.pacientes != null) {
            this.pacientes.remove(paciente);
        }
    }

    public Paciente getPacienteByHistoriaClinica(String historiaClinica) {
        if (this.pacientes != null) {
            for (Paciente paciente : this.pacientes) {
                if (paciente.getHistoriaClinica().equals(historiaClinica)) {
                    return paciente;
                }
            }
        }
        return null;
    }
}