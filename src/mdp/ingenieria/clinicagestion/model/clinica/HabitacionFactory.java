package mdp.ingenieria.clinicagestion.model.clinica;

import mdp.ingenieria.clinicagestion.model.clinica.habitacion.HabitacionCompartida;
import mdp.ingenieria.clinicagestion.model.clinica.habitacion.HabitacionPrivada;
import mdp.ingenieria.clinicagestion.model.clinica.habitacion.HabitacionTerapiaIntensiva;

/**
 * Fábrica de objetos Habitacion.
 */
public class HabitacionFactory {
    public static final String HABITACION_COMPARTIDA = "compartida";
    public static final String HABITACION_PRIVADA = "privada";
    public static final String HABITACION_TERAPIA_INTENSIVA = "terapia_intensiva";

    /**
     * Crea una habitación del tipo indicado.
     *
     * <b>pre:</b> tipo no es nulo ni vacío; el tipo existe; costo asigancion >= 0; costo adicional >= 0 <br>
     * <b>post:</b> devuelve una instancia de Habitacion del tipo solicitado.
     *
     * @param tipo                  identificador textual del tipo de habitación
     * @param costoAsignacion       costo base por asignación
     * @param costoAdicional        costo adicional (interpretación según el tipo concreto)
     * @return instancia concreta de Habitacion
     */
    public Habitacion create(String tipo, double costoAsignacion, double costoAdicional) {
        Habitacion habitacion = null;

        switch(tipo){
            case HABITACION_COMPARTIDA: habitacion = new HabitacionCompartida(costoAsignacion,costoAdicional); break;
            case HABITACION_PRIVADA: habitacion = new HabitacionPrivada(costoAsignacion,costoAdicional); break;
            case HABITACION_TERAPIA_INTENSIVA: habitacion = new HabitacionTerapiaIntensiva(costoAsignacion,costoAdicional); break;
        }

        return habitacion;
    }
}
