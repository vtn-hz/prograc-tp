package mdp.ingenieria.clinicagestion.model.clinica.habitacion;
import java.lang.Math;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

/**
 * Representa una habitación de terapia intensiva dentro de la clínica.
 * Extiende la clase Habitación
 */
public class HabitacionTerapiaIntensiva extends Habitacion 
{

    /**
     * Constructor de una habitación de terapia intensiva.
     * <b>pre:</b> costoAsignacion y costoAdicional deben ser mayores o iguales a cero <br>
     * <b>post:</b> inicializa una instancia de habitación de terapia intensiva con los valores indicados
     *
     * @param costoAsignacion   costo base por asignación de la habitación
     * @param costoAdicional    costo adicional base utilizado en el cálculo exponencial
     */
    public HabitacionTerapiaIntensiva(double costoAsignacion, double costoAdicional)
    {
        super(costoAsignacion,costoAdicional);
    }

    /**
     * Calcula el costo adicional aplicando un crecimiento potencial respecto al número de días.
     * Utiliza la fórmula: costoAdicional ^ dias
     *
     * <b>pre:</b> dias debe ser mayor a 0 <br>
     * <b>post:</b> devuelve el valor total del costo adicional según el crecimiento exponencial
     *
     * @param dias      número de días que el paciente ocupa la habitación
     * @return costo adicional total calculado exponencialmente
     */
    @Override
    public double getCostoAdicional(int dias)
    {
        return Math.pow(this.costoAdicional,(double)dias);
    }

    /**
     * Retorna el tipo de habitación.
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el string "terapia intensiva"
     *
     * @return tipo de habitación: "terapia intensiva"
     */
	@Override
	public String getTipoHabitacion() {
		return "terapia intensiva";
	}
}
