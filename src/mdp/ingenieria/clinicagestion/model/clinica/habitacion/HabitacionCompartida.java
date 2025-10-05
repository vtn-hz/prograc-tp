package mdp.ingenieria.clinicagestion.model.clinica.habitacion;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;

public class HabitacionCompartida extends Habitacion{

    public HabitacionCompartida(double costoAsignacion,double costoAdicional) {
        super(costoAsignacion,costoAdicional);
    }
    
    @Override
    public double getCostoAdicional(int dias) {
        return this.costoAdicional*dias;
    }

	@Override
	public String getTipoHabitacion() {
		return "compartida";
	}
}
