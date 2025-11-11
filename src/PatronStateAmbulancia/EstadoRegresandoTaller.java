package PatronStateAmbulancia;

public class EstadoRegresandoTaller extends EstadoAbstracto{
    public EstadoRegresandoTaller(Ambulancia ambulancia) {
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
        // informa que no puede
    }


}
