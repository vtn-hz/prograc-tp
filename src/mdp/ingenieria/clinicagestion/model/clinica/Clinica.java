package mdp.ingenieria.clinicagestion.model.clinica;

import mdp.ingenieria.clinicagestion.model.Domicilio;

/**
 * Representa la clínica principal del sistema
 * Implementa el patrón Singleton para garantizar que solo exista una instancia
 */
public class Clinica {
	
    private static Clinica _instance;
    
    private Domicilio domicilio;
    
    private String nombre;

	/**
     * Constructor privado
     * Solo puede ser invocado internamente por el metodo getInstancia
  	 *
     * <b>post:</b> inicializa la clínica con los valores indicados
     * 
	 * @param nombre
	 * @param telefono
	 * @param ciudad
	 * @param direccion
	 */
    private Clinica(String nombre, String telefono, String ciudad, String direccion)
    {
        this.domicilio = new Domicilio(telefono, ciudad, direccion);
        this.nombre = nombre;
    }
    
    /**
     * <b>post: </b> inicia clinica singleton
     * 
     * @param nombre
     * @param telefono
     * @param ciudad
     * @param direccion
     */
    public static void initialize(String nombre, String telefono, String ciudad, String direccion)
    {
    	Clinica._instance = new Clinica(nombre, telefono, ciudad, direccion);
    }

    /**
     * Retorna la única instancia de Clinica
     *
     * <b>pre:</b> la clinica se encuentra inicializada <br>
     * 
     * @return instancia única de Clinica
     */
    public static Clinica getInstance()
    {
        return _instance;
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
