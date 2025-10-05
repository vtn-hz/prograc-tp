package mdp.ingenieria.clinicagestion.service;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegistroMedico {
    private IMedico medico;
    private ArrayList<MedicoConsulta> consultasEfectuadas;

    public RegistroMedico(IMedico medico) {
        this.medico = medico;
        this.consultasEfectuadas = new ArrayList<>();
    }

    public IMedico getMedico() {
        return medico;
    }

    public ArrayList<MedicoConsulta> getConsultasEfectuadas() {
        return consultasEfectuadas;
    }

    public ArrayList<MedicoConsulta> getConsultasEfectuadasByFecha(LocalDateTime fechai, LocalDateTime fechaf) {
        ArrayList<MedicoConsulta> rangoConsultas = new ArrayList<>();

        for (MedicoConsulta cons : consultasEfectuadas) {
            LocalDateTime fecha = cons.getFecha();
            if ((fecha.isEqual(fechai) || fecha.isAfter(fechai)) && (fecha.isEqual(fechaf) || fecha.isBefore(fechaf))) {
                rangoConsultas.add(cons);
            }
        }

        return rangoConsultas;
    }

    public void addConsulta(LocalDateTime fecha, String nombre) {
        MedicoConsulta cons = new MedicoConsulta(fecha, nombre, medico.getHonorario());
        consultasEfectuadas.add(cons);
    }
}
