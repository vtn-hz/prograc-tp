package PatronStateAmbulancia;

public abstract class EstadoAbstracto implements IEstadoAmbulancia{
    protected Ambulancia ambulancia;
    public EstadoAbstracto(Ambulancia ambulancia){
        this.ambulancia = ambulancia;
    }

    public boolean puedeAtencionDomicilio(){ return false; }
    public boolean puedeTraslado(){ return false; }
    public boolean puedeMantenimiento(){ return false; }
}
