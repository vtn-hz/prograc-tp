package sistema.gestion.clinica.modelo;

import java.util.ArrayList;

public class GestorAtencionPaciente {
    private ArrayList<Paciente> pacientesEspera;
    private ArrayList<Paciente> pacientesAtencion;

    public GestorAtencionPaciente() {
        this.pacientesEspera = new ArrayList<>();
        this.pacientesAtencion = new ArrayList<>();
    }

    public void anunciar(Paciente paciente) {
        if (this.pacientesEspera == null) {
            this.pacientesEspera = new ArrayList<>();
        }
        this.pacientesEspera.add(paciente);
        SalaEsperaPatio.getInstance().ocupar(paciente);
    }

    public void atender(Paciente paciente) {
        if (this.pacientesAtencion == null) {
            this.pacientesAtencion = new ArrayList<>();
        }
        this.pacientesAtencion.add(paciente);
    }

    public void egresar(Paciente paciente) {
        if (this.pacientesAtencion != null) {
            this.pacientesAtencion.remove(paciente);
        }
    }

    public Paciente getSigPacienteAtender() {
        if (this.pacientesEspera != null && !this.pacientesEspera.isEmpty()) {
            return this.pacientesEspera.remove(0);
        }
        return null;
    }
}