package mdp.ingenieria.clinicagestion.model.clinica.habitacion;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

/**
 * Representa una habitación compartida dentro de la clínica.
 * Extiende la clase Habitación
 */
public class HabitacionCompartida extends Habitacion{

    /**
     * Constructor de una habitación compartida.
     * <b>pre:</b> costoAsignacion y costoAdicional deben ser mayores o iguales a cero <br>
     * <b>post:</b> inicializa una instancia de habitación compartida con los valores indicados
     *
     * @param costoAsignacion   costo base por asignación de la habitación
     * @param costoAdicional    costo adicional por día de estadía
     */
    public HabitacionCompartida(double costoAsignacion,double costoAdicional) {
        super(costoAsignacion,costoAdicional);
    }

    /**
     * Calcula el costo adicional según la cantidad de días de estadía.
     * <b>pre:</b> dias debe ser mayor a 0 <br>
     * <b>post:</b> devuelve el valor total del costo adicional multiplicando el costo diario por la cantidad de días
     *
     * @param dias      número de días que el paciente ocupa la habitación
     * @return costo adicional total correspondiente al período indicado
     */
    @Override
    public double getCostoAdicional(int dias) {
        return this.costoAdicional*dias;
    }

    /**
     * Retorna el tipo de habitación.
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el String "compartida"
     *
     * @return tipo de habitación: "compartida"
     */
	@Override
	public String getTipoHabitacion() {
		return "compartida";
	}
}
