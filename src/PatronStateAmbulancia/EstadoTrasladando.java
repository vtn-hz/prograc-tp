package PatronStateAmbulancia;

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
}
