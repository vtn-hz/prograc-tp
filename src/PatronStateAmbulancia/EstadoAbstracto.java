package PatronStateAmbulancia;

public abstract class EstadoAbstracto implements IEstadoAmbulancia{
    protected Ambulancia ambulancia;
    public EstadoAbstracto(Ambulancia ambulancia){
        this.ambulancia = ambulancia;
    }
}
