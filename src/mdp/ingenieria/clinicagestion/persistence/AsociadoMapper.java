package mdp.ingenieria.clinicagestion.persistence;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.Domicilio;

public final class AsociadoMapper {
    private AsociadoMapper() {}

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