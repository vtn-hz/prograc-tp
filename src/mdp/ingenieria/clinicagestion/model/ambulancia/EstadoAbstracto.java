package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.Ambulancia;

public abstract class EstadoAbstracto {
    protected Ambulancia ambulancia;

    public EstadoAbstracto(Ambulancia ambulancia){
        this.ambulancia = ambulancia;
    }

    public boolean puedeTraslado(){ return false; }

    public boolean puedeMantenimiento(){ return false; }

    public boolean puedeAtencionDomicilio(){ return false; }

    public abstract void solicitarTraslado();

    public abstract void retornoAutomatico();

    public abstract void solicitarMantenimiento();

    public abstract void solicitarAtencionDomicilio();
    
    @Override
    public abstract String toString();
}
