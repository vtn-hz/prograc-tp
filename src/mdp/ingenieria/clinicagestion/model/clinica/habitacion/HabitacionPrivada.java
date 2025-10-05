package mdp.ingenieria.clinicagestion.model.clinica.habitacion;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

public class HabitacionPrivada extends Habitacion{
    public HabitacionPrivada(double costoAsignacion, double costoAdicional)
    {
        super(costoAsignacion,costoAdicional);
    }
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
}
