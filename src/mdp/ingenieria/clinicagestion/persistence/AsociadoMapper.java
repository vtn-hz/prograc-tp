package mdp.ingenieria.clinicagestion.persistence;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public final class AsociadoMapper {
    private AsociadoMapper() {}

    /**
     * Convierte un objeto Asociado del modelo a su versión DTO.
     *
     * <b>pre:</b> a no debe ser nulo <br>
     * <b>post:</b> se devuelve un DTO con los datos del asociado,
     *
     * @param a objeto Asociado del modelo
     * @return instancia de AsociadoDTO con los datos equivalentes
     */
    public static AsociadoDTO toDTO(Asociado a) {
        String dni = a.getDni();                // de Persona
        String nya = a.getNyA();                // de Persona
        Domicilio d = a.getDomicilio();         // de Persona
        String ciudad = d != null ? d.getCiudad() : null;
        String direccion = d != null ? d.getDireccion() : null;
        String telefono = d != null ? d.getTelefono() : null; // si no existe, usa null
        return new AsociadoDTO(dni, nya, telefono, ciudad, direccion);
    }
}