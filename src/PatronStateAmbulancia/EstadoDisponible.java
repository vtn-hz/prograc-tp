package PatronStateAmbulancia;

public class EstadoDisponible extends EstadoAbstracto {
    public EstadoDisponible(Ambulancia ambulancia) {
        super(ambulancia);
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
    public void solicitarMantenimiento() {
        this.ambulancia.setEstado(new EstadoEnTaller(this.ambulancia));
    }

    @Override
    public void retornoAutomatico() {
        // permanece en este estado
    }
}
