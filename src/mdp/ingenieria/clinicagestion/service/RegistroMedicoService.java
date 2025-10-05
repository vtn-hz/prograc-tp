package mdp.ingenieria.clinicagestion.service;

import mdp.ingenieria.clinicagestion.exception.MedicoMatriculaDuplicadaException;
import mdp.ingenieria.clinicagestion.exception.MedicoNoRegistradoException;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistroMedicoService {
    private HashMap<Integer, RegistroMedico> medicos;

    public RegistroMedicoService() {
        this.medicos = new HashMap<>();
    }

    public void registraMedico(IMedico medico) throws MedicoMatriculaDuplicadaException {
        int matriculaIngresada = medico.getNumeroMatricula();

        if (medicos.containsKey(matriculaIngresada))
            throw new MedicoMatriculaDuplicadaException(matriculaIngresada);

        RegistroMedico registro = new RegistroMedico(medico);
        medicos.put(matriculaIngresada, registro);
    }

    public RegistroMedico buscarMedico(IMedico medico) throws MedicoNoRegistradoException {
        int matriculaBuscada = medico.getNumeroMatricula();

        for (Integer matricula : medicos.keySet()) { // busca entre las keys
            if (matricula == matriculaBuscada) {
                return medicos.get(matricula);
            }
        }

        throw new MedicoNoRegistradoException(matriculaBuscada);
    }

    public void actualizarConsultasMedicos(ArrayList<IMedico> medicosConsultados, String nombre, LocalDateTime fecha) throws MedicoNoRegistradoException {
        for (IMedico medico : medicosConsultados) {
            RegistroMedico registro = buscarMedico(medico);
            registro.addConsulta(fecha, nombre);
        }
    }

    public boolean isRegistrado(IMedico medico) {
        return medicos.containsKey(medico.getNumeroMatricula());
    }
}
