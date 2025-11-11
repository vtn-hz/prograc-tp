package mdp.ingenieria.clinicagestion.model.ambulancia;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.persona.Operario;

import java.util.Observable;

public class Ambulancia extends Observable {
    private EstadoAbstracto estado;

    public Ambulancia(){
         this.estado = new EstadoDisponible(this);
    }

    public synchronized void solicitarAtencionDomicilio(Asociado asociado){
        while( !estado.puedeAtencionDomicilio() ){
            try{
                this.notifyObservers(new AmbulanceStateMessage("No se puede atender a domicilio en este momento", asociado) );
                wait();

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.estado.solicitarAtencionDomicilio();
        setChanged();
        notifyObservers( new AmbulanceStateMessage("Se solicito atencion a domicilio", asociado));
        notifyAll();
    }

    public synchronized void solicitarTraslado(Asociado asociado){
        while (!estado.puedeTraslado()) {
            try {
                this.notifyObservers( new AmbulanceStateMessage("No se puede realizar el traslado en este momento", asociado));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarTraslado();
        setChanged();
        notifyObservers(new AmbulanceStateMessage("Se solicito traslado", asociado));
        notifyAll();
    }

    public synchronized void solicitarMantenimiento(Operario operario){
        while (!estado.puedeMantenimiento()) {
            try {
                this.notifyObservers(new AmbulanceStateMessage("No se puede realizar el mantenimiento en este momento", operario));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarMantenimiento();
        setChanged();
        notifyObservers(new AmbulanceStateMessage("Se solicito mantenimiento", operario));
        notifyAll();
    }

    public synchronized void retornoAutomatico(){
        this.estado.retornoAutomatico();
        setChanged();
        notifyObservers( new AmbulanceStateMessage("Ambulancia en retorno automatico") );
        notifyAll();
    }

    public synchronized void setEstado(EstadoAbstracto estado) {
        this.estado = estado;
        notifyAll();
    }

}
