package mdp.ingenieria.clinicagestion.model.clinica;

/**
 * Representa la clínica principal del sistema
 * Implementa el patrón Singleton para garantizar que solo exista una instancia
 */
public class Clinica {
    private Clinica _instancia;
    private Domicilio domicilio;
    private String nombre;

    /**
     * Constructor privado
     * Solo puede ser invocado internamente por el metodo getInstancia
     *
     * <b>pre:</b> domicilio y nombre no deben ser nulos ni vacíos <br>
     * <b>post:</b> inicializa la clínica con los valores indicados
     *
     * @param domicilio         domicilio de la clínica
     * @param nombre            nombre de la clínica
     */
    private Clinica(Domicilio domicilio, String nombre) {
        this.domicilio = domicilio;
        this.nombre = nombre;
    }

    /**
     * Retorna la única instancia de Clinica
     * Si aún no existe, la crea con los datos proporcionados.
     *
     * <b>pre:</b> domicilio y nombre no deben ser nulos ni vacíos <br>
     * <b>post:</b> garantiza que siempre haya una única instancia
     *
     * @param domicilio         domicilio físico de la clínica
     * @param nombre            nombre de la clínica
     * @return instancia única de Clinica
     */
    public Clinica getInstancia(Domicilio domicilio, String nombre) {
        if (_instancia == null) {
            _instancia = new Clinica(domicilio, nombre);
        }
        return _instancia;
    }

    /**
     * Retorna el domicilio de la clínica.
     *
     * <b>pre:</b> la clínica debe haber sido inicializada correctamente <br>
     * <b>post:</b> devuelve el objeto Domicilio asociado a la clínica
     *
     * @return domicilio de la clínica
     */
    public Domicilio getDomicilio() {
        return domicilio;
    }

    /**
     * Retorna el nombre de la clínica.
     *
     * <b>pre:</b> la clínica debe haber sido inicializada correctamente <br>
     * <b>post:</b> devuelve el nombre asignado a la clínica
     *
     * @return nombre de la clínica
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una representación en texto del objeto Clinica
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve una cadena con el nombre y domicilio de la clínica
     *
     * @return descripción textual de la clínica
     */
    @Override
    public String toString() {
        return "Clinica{" +
                "domicilio=" + domicilio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
