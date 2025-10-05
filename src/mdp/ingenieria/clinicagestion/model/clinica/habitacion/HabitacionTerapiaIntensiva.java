package mdp.ingenieria.clinicagestion.model.clinica.habitacion;
import java.lang.Math;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

public class HabitacionTerapiaIntensiva extends Habitacion{
    public HabitacionTerapiaIntensiva(double costoAsignacion, double costoAdicional)
    {
        super(costoAsignacion,costoAdicional);
    }
    @Override
    public double getCostoAdicional(int dias)
    {
        return Math.pow(this.costoAdicional,(double)dias);
    }
}
