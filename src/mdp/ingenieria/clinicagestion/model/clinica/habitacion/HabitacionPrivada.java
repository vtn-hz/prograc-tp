package mdp.ingenieria.clinicagestion.model.clinica.habitacion;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
/**
 * Representa una habitación privada dentro de la clínica.
 * Extiende la clase Habitación
 */
public class HabitacionPrivada extends Habitacion
{
    /**
     * Constructor de una habitación privada.
     * <b>pre:</b> costoAsignacion y costoAdicional deben ser mayores o iguales a cero <br>
     * <b>post:</b> inicializa una instancia de habitación privada con los valores indicados
     *
     * @param costoAsignacion   costo base por asignación de la habitación
     * @param costoAdicional    costo adicional por día de estadía
     */
    public HabitacionPrivada(double costoAsignacion, double costoAdicional)
    {
        super(costoAsignacion,costoAdicional);
    }

    /**
     * Calcula el costo adicional según la cantidad de días de estadía.
     * Aplica incrementos por mayor duración:
     * <ul>
     *   <li>Entre 2 y 5 días: incremento del 30%</li>
     *   <li>Más de 6 días: incremento del 100%</li>
     * </ul>
     *
     * <b>pre:</b> dias debe ser mayor a 0 <br>
     * <b>post:</b> devuelve el valor total del costo adicional ajustado según la cantidad de días
     *
     * @param dias      número de días que el paciente ocupa la habitación
     * @return costo adicional total correspondiente al período indicado
     */
    @Override
    public double getCostoAdicional(int dias)
    {
        double costo = this.costoAdicional;
        if(dias>=2 && dias<=5)
            costo*=1.3;
        else
            if(dias>6)
                costo*=2;
        return costo*dias;
    }

    /**
     * Retorna el tipo de habitación.
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el string "privada"
     *
     * @return tipo de habitación: "privada"
     */
	@Override
	public String getTipoHabitacion() {
		return "privada";
	}
}
