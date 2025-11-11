package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public class EstadoRegresando extends EstadoAbstracto {
    public EstadoRegresando(Ambulancia ambulancia) {
        super(ambulancia);
    }

    @Override
    public boolean puedeTraslado() {
        return true;
    }

    @Override
    public boolean puedeAtencionDomicilio() {
        return true;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        this.ambulancia.setEstado(new EstadoAtendiendo(this.ambulancia));
    }

    @Override
    public void solicitarTraslado() {
        this.ambulancia.setEstado(new EstadoTrasladando(this.ambulancia));
    }

    @Override
    public void retornoAutomatico() {
        this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
        // informa que no puede
    }
}
