package mdp.ingenieria.clinicagestion.persistence;

public class AsociadoDTO extends PersonaDTO {
    /**
     * Crea un DTO de asociado con los datos básicos.
     *
     * <b>pre:</b> todos los parámetros deben ser no nulos ni vacíos <br>
     * <b>post:</b> se inicializa el objeto con los valores proporcionados
     *
     * @param dni documento del asociado
     * @param nya nombre y apellido
     * @param telefono teléfono de contacto
     * @param ciudad ciudad de residencia
     * @param direccion dirección del asociado
     */
    public AsociadoDTO(String dni, String nya, String telefono, String ciudad, String direccion)
    {
        super(dni, nya, telefono, ciudad, direccion);
    }
}