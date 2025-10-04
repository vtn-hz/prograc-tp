package sistema.gestion.clinica.clinica;
import java.lang.Math;

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
