package PatronStateAmbulancia;

public class Ambulancia {
    private IEstadoAmbulancia estado = new EstadoDisponible(this);
    public void solicitarAtencionDomicilio(){
        this.estado.solicitarAtencionDomicilio();
    }
    public void solicitarTraslado(){
        this.estado.solicitarTraslado();
    }
    public void solicitarMantenimiento(){
        this.estado.solicitarMantenimiento();
    }
    public void retornoAutomatico(){
        this.estado.retornoAutomatico();
    }

    public void setEstado(IEstadoAmbulancia estado) {
        this.estado = estado;
    }
}
