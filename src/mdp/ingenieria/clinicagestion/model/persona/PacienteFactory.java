package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteMayor;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteNino;
import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteJoven;
/**
 * Fábrica de objetos Paciente de distintos tipos (Niño, Joven, Mayor).
 */
public class PacienteFactory {
    
    public static final String PACIENTE_NINO = "nino";
    public static final String PACIENTE_JOVEN = "joven";
    public static final String PACIENTE_MAYOR = "mayor";

    /**
     * Crea un paciente del tipo indicado.
     *
     * <b>pre:</b> todos los parámetros String no deben ser nulos ni vacíos; el tipo debe existir <br>
     * <b>post:</b> retorna una instancia de {@link Paciente} del tipo solicitado
     *
     * @param NyA                   nombre y apellido del paciente
     * @param dni                   documento de identidad
     * @param telefono              teléfono de contacto
     * @param ciudad                ciudad del domicilio
     * @param direccion             dirección del domicilio
     * @param nroHistoriaClinica    número de historia clínica
     * @param tipo                  identificador String del tipo de paciente
     * @return instancia de Paciente del tipo solicitado
     */
    public Paciente create(
        String NyA, String dni,
        String telefono, String ciudad, String direccion,
        int nroHistoriaClinica,
        String tipo
    ) {
        
        Domicilio domicilio = new Domicilio(telefono, ciudad, direccion);
        Paciente paciente = null;
        
        switch (tipo.toLowerCase().trim()) {
            case PACIENTE_NINO:
                paciente = new PacienteNino(NyA, dni, domicilio, nroHistoriaClinica);
                break;
            case PACIENTE_JOVEN:
                paciente = new PacienteJoven(NyA, dni, domicilio, nroHistoriaClinica);
                break;
            case PACIENTE_MAYOR:
                paciente = new PacienteMayor(NyA, dni, domicilio, nroHistoriaClinica);
                break;
        }
        
        return paciente;
    }
    
    /**
     * Crea un paciente del tipo indicado.
     *
     * <b>pre:</b> todos los parámetros String no deben ser nulos ni vacíos; el tipo debe existir <br>
     * <b>post:</b> retorna una instancia de {@link Paciente} del tipo solicitado
     *
     * @param NyA                   nombre y apellido del paciente
     * @param dni                   documento de identidad
     * @param domicilio             domicilio del paciente
     * @param nroHistoriaClinica    número de historia clínica
     * @param tipo                  identificador String del tipo de paciente
     * @return instancia de Paciente del tipo solicitado
     */
    public Paciente create(
        String NyA, String dni,
        Domicilio domicilio,
        int nroHistoriaClinica,
        String tipo
    ) { 
        Paciente paciente = null;
        
        switch (tipo.toLowerCase().trim()) {
            case PACIENTE_NINO:
                paciente = new PacienteNino(NyA, dni, domicilio, nroHistoriaClinica);
                break;
            case PACIENTE_JOVEN:
                paciente = new PacienteJoven(NyA, dni, domicilio, nroHistoriaClinica);
                break;
            case PACIENTE_MAYOR:
                paciente = new PacienteMayor(NyA, dni, domicilio, nroHistoriaClinica);
                break;
        }
        return paciente;
    }

    /**
     * Crea un paciente de tipo Niño.
     * <p>
     * Método de conveniencia para creación directa sin especificar tipo.
     * </p>
     *
     * <b>pre:</b> todos los parámetros deben ser válidos <br>
     * <b>post:</b> retorna una instancia de PacienteNino
     *
     * @param NyA                       nombre y apellido
     * @param dni                       documento de identidad
     * @param telefono                  teléfono de contacto
     * @param ciudad                    ciudad del domicilio
     * @param direccion                 dirección del domicilio
     * @param nroHistoriaClinica        número de historia clínica
     * @return instancia de PacienteNino
     */
    public PacienteNino createNino(
        String NyA, String dni,
        String telefono, String ciudad, String direccion,
        int nroHistoriaClinica
    ) {
        Domicilio domicilio = new Domicilio(telefono, ciudad, direccion);
        return new PacienteNino(NyA, dni, domicilio, nroHistoriaClinica);
    }
    
    /**
     * Crea un paciente de tipo Joven.
     * <p>
     * Método de conveniencia para creación directa sin especificar tipo.
     * </p>
     *
     * <b>pre:</b> todos los parámetros deben ser válidos <br>
     * <b>post:</b> retorna una instancia de PacienteJoven
     *
     * @param NyA                       nombre y apellido
     * @param dni                       documento de identidad
     * @param telefono                  teléfono de contacto
     * @param ciudad                    ciudad del domicilio
     * @param direccion                 dirección del domicilio
     * @param nroHistoriaClinica        número de historia clínica
     * @return instancia de PacienteJoven
     */
    public PacienteJoven createJoven(
        String NyA, String dni,
        String telefono, String ciudad, String direccion,
        int nroHistoriaClinica
    ) {
        Domicilio domicilio = new Domicilio(telefono, ciudad, direccion);
        return new PacienteJoven(NyA, dni, domicilio, nroHistoriaClinica);
    }
    
    /**
     * Crea un paciente de tipo Mayor.
     * <p>
     * Método de conveniencia para creación directa sin especificar tipo.
     * </p>
     *
     * <b>pre:</b> todos los parámetros deben ser válidos <br>
     * <b>post:</b> retorna una instancia de PacienteMayor
     *
     * @param NyA                       nombre y apellido
     * @param dni                       documento de identidad
     * @param telefono                  teléfono de contacto
     * @param ciudad                    ciudad del domicilio
     * @param direccion                 dirección del domicilio
     * @param nroHistoriaClinica        número de historia clínica
     * @return instancia de PacienteMayor
     */
    public PacienteMayor createMayor(
        String NyA, String dni,
        String telefono, String ciudad, String direccion,
        int nroHistoriaClinica
    ) {
        Domicilio domicilio = new Domicilio(telefono, ciudad, direccion);
        return new PacienteMayor(NyA, dni, domicilio, nroHistoriaClinica);
    }
}