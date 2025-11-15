package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoTrasladando extends EstadoAbstracto{
    public EstadoTrasladando(Ambulancia ambulancia) {
        super(ambulancia);
    }
    @Override
    public void solicitarAtencionDomicilio() {
        // permanece en este estado
    }

    @Override
    public void solicitarTraslado() {
        // informa que no puede
    }

    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
        // infroma que no puede
    }
	@Override
	public String toString() {
		return "Trasladando";
	}   
}
