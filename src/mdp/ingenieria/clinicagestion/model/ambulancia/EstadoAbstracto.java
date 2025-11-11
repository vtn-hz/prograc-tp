package mdp.ingenieria.clinicagestion.model.ambulancia;

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
}
