package mdp.ingenieria.clinicagestion.model.clinica;

/**
 * Clase abstracta que representa una habitación dentro de la clínica.
 * Define los atributos y comportamientos comunes a todas las habitaciones.
 */
public abstract class Habitacion
{
    
	protected double costoAsignacion;
	
	protected double costoAdicional;

    /**
     * Constructor de una habitación genérica.
     *
     * <b>pre:</b> costoAsignacion y costoAdicional deben ser mayores o iguales a cero <br>
     * <b>post:</b> inicializa la habitación con los costos indicados
     *
     * @param costoAsignacion       costo base por la asignación de la habitación
     * @param costoAdicional        costo adicional asociado a la estadía
     */
    public Habitacion(double costoAsignacion, double costoAdicional) {
        this.costoAsignacion = costoAsignacion;
        this.costoAdicional = costoAdicional;
    }

    /**
     * Retorna el costo base de asignación de la habitación.
     *
     * <b>pre:</b> la habitación debe haber sido correctamente inicializada <br>
     * <b>post:</b> devuelve el valor del costo base de asignación
     *
     * @return costo de asignación de la habitación
     */
    public double getCostoAsignacion() {
        return costoAsignacion;
    }

    /**
     * Calcula el costo adicional según la cantidad de días de estadía.
     * Este metodo debe ser implementado por las subclases de Habitacion.
     *
     * <b>pre:</b> dias debe ser mayor que 0 <br>
     * <b>post:</b> devuelve el costo adicional correspondiente a los días especificados
     *
     * @param dias      número de días que el paciente ocupa la habitación
     * @return costo adicional total por la estadía
     */
    public abstract double getCostoAdicional(int dias);

    /**
     * Calcula el costo total: costo de asignación + el costo adicional.
     *
     * <b>pre:</b> dias debe ser mayor que 0 <br>
     * <b>post:</b> devuelve la suma del costo base y el costo adicional calculado
     *
     * @param dias      número de días que el paciente ocupa la habitación
     * @return costo total de la habitación para el período indicado
     */
    public double getCostoTotal(int dias){
        return this.getCostoAsignacion()+this.getCostoAdicional(dias);
    }

    /**
     * Retorna el tipo de habitación.
     * Este metodo debe ser implementado por las subclases.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve una cadena representando el tipo de habitación
     *
     * @return tipo de habitación en formato de String.
     */
    public abstract String getTipoHabitacion();
}
