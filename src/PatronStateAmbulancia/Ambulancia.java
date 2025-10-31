package PatronStateAmbulancia;

import java.util.Observable;

public class Ambulancia extends Observable {
    private IEstadoAmbulancia estado = new EstadoDisponible(this);
    public synchronized void solicitarAtencionDomicilio(){
        while (!estado.puedeAtencionDomicilio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarAtencionDomicilio();
        setChanged();
        notifyObservers("Se solicito atencion domicilio");
        notifyAll();
    }

    public synchronized void solicitarTraslado(){
        while (!estado.puedeTraslado()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarTraslado();
        setChanged();
        notifyObservers("Se solicito traslado");
        notifyAll();
    }

    public synchronized void solicitarMantenimiento(){
        while (!estado.puedeMantenimiento()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarMantenimiento();
        setChanged();
        notifyObservers("Se solicito mantenimiento");
        notifyAll();
    }

    public synchronized void retornoAutomatico(){
        this.estado.retornoAutomatico();
        setChanged();
        notifyObservers("Se solicito retorno automatico");
        notifyAll();
    }

    public synchronized void setEstado(IEstadoAmbulancia estado) {
        this.estado = estado;
        setChanged();
        notifyObservers("Nuevo estado");
        notifyAll();
    }

}
