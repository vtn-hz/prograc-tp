package mdp.ingenieria.clinicagestion.model.ambulancia;

public class EstadoEnTaller extends EstadoAbstracto{
    public EstadoEnTaller(Ambulancia ambulancia) {
        super(ambulancia);
    }

    @Override
    public boolean puedeMantenimiento(){
        return true;
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
        // permanece en este estado
    }

    @Override
    public void solicitarMantenimiento() {
        this.ambulancia.setEstado(new EstadoRegresandoTaller(this.ambulancia));
    }
}
