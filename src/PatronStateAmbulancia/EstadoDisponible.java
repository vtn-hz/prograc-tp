package PatronStateAmbulancia;

public class EstadoDisponible extends EstadoAbstracto {
    public EstadoDisponible(Ambulancia ambulancia) {
        super(ambulancia);
    }

    @Override
    public boolean puedeAtencionDomicilio(){ return true; }
    @Override
    public boolean puedeTraslado(){ return true; }
    @Override
    public boolean puedeMantenimiento(){ return true; }

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
