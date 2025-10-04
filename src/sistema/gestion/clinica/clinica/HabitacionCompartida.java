package sistema.gestion.clinica.clinica;

public class HabitacionCompartida extends Habitacion{

    public HabitacionCompartida(double costoAsignacion,double costoAdicional) {
        super(costoAsignacion,costoAdicional);
    }
    @Override
    public double getCostoAdicional(int dias) {
        return this.costoAdicional*dias;
    }
}
