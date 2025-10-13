package mdp.ingenieria.clinicagestion.service;

import mdp.ingenieria.clinicagestion.exception.MedicoMatriculaDuplicadaException;
import mdp.ingenieria.clinicagestion.exception.MedicoNoRegistradoException;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.RegistroMedico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servicio responsable de registrar médicos y mantener sus registros de consultas.
 */
public class RegistroMedicoService {
    private HashMap<Integer, RegistroMedico> medicos;

    /**
     * Constructor del servicio con el índice vacío.
     * <b>post:</b> estructura interna inicializada sin médicos registrados
     */
    public RegistroMedicoService() {
        this.medicos = new HashMap<>();
    }

    /**
     * Registra un médico nuevo en el sistema.
     *
     * <b>pre:</b> medico no debe ser null <br>
     * <b>post:</b> se crea y almacena un RegistroMedico asociado a la matrícula
     *
     * @param medico profesional a registrar
     * @throws MedicoMatriculaDuplicadaException si la matrícula ya está registrada
     */
    public void registraMedico(IMedico medico) throws MedicoMatriculaDuplicadaException {
        int matriculaIngresada = medico.getNumeroMatricula();

        if (medicos.containsKey(matriculaIngresada))
            throw new MedicoMatriculaDuplicadaException(matriculaIngresada);

        RegistroMedico registro = new RegistroMedico(medico);
        medicos.put(matriculaIngresada, registro);
    }

    /**
     * Busca el registro del médico por su matrícula.
     *
     * <b>pre:</b> medico no debe ser null <br>
     * <b>post:</b> devuelve el RegistroMedico si existe
     *
     * @param medico médico cuya matrícula se busca
     * @return registro asociado a la matrícula del médico
     * @throws MedicoNoRegistradoException si no existe un médico con esa matrícula
     */
    public RegistroMedico buscarMedico(IMedico medico) throws MedicoNoRegistradoException {
        int matriculaBuscada = medico.getNumeroMatricula();

        for (Integer matricula : medicos.keySet()) { // busca entre las keys
            if (matricula == matriculaBuscada) {
                return medicos.get(matricula);
            }
        }

        throw new MedicoNoRegistradoException(matriculaBuscada);
    }

    /**
     * Actualiza los registros de los médicos agregando una consulta con la fecha y el nombre del paciente.
     *
     * <b>pre:</b> medicosConsultados no debe ser null; nombre no debe ser nulo ni vacío; fecha no debe ser null;
     * todos los médicos deben estar registrados <br>
     * <b>post:</b> en cada RegistroMedico se agrega una nueva consulta
     *
     * @param medicosConsultados        lista de médicos que atendieron
     * @param nombre                    nombre del paciente atendido
     * @param fecha                     fecha y hora de la consulta
     * @throws MedicoNoRegistradoException si alguno de los médicos no está registrado
     */
    public void actualizarConsultasMedicos(ArrayList<IMedico> medicosConsultados, String nombre, LocalDateTime fecha) throws MedicoNoRegistradoException {
        for (IMedico medico : medicosConsultados) {
            RegistroMedico registro = buscarMedico(medico);
            registro.addConsulta(fecha, nombre);
        }
    }

    /**
     * Indica si un médico se encuentra registrado por su matrícula.
     *
     * <b>pre:</b> medico no debe ser null <br>
     * <b>post:</b> devuelve true si la matrícula está en el índice
     *
     * @param medico médico a consultar
     * @return true si está registrado; caso contrario false
     */
    public boolean isRegistrado(IMedico medico) {
        return medicos.containsKey(medico.getNumeroMatricula());
    }
}
