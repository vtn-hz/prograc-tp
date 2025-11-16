package mdp.ingenieria.clinicagestion.model;

import mdp.ingenieria.clinicagestion.model.ambulancia.AmbulanceStateMessage;
import mdp.ingenieria.clinicagestion.model.ambulancia.EstadoAbstracto;
import mdp.ingenieria.clinicagestion.model.ambulancia.EstadoDisponible;
import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;

import java.util.Observable;

public class Ambulancia extends Observable {
    private EstadoAbstracto estado;

    public Ambulancia(){
        this.estado = new EstadoDisponible(this);
    }

    public synchronized void solicitarAtencionDomicilio(Asociado asociado){
        while( Simulation.getInstance().isRunning() && !estado.puedeAtencionDomicilio() ){
            try{
            	setChanged();
                this.notifyObservers(
                	new AmbulanceStateMessage(
                		"No se puede atender a domicilio en este momento",
                		Actor.PENDING, asociado
                	)
                );
                wait();

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.estado.solicitarAtencionDomicilio();
        setChanged();
        notifyObservers( new AmbulanceStateMessage("Se solicito atencion a domicilio", Actor.RUNNING, asociado));
        notifyAll();
    }

    public synchronized void solicitarTraslado(Asociado asociado){
        while ( Simulation.getInstance().isRunning() && !estado.puedeTraslado()) {
            try {
            	setChanged();
                this.notifyObservers( new AmbulanceStateMessage("No se puede realizar el traslado en este momento", Actor.PENDING, asociado));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarTraslado();
        setChanged();
        notifyObservers(new AmbulanceStateMessage("Se solicito traslado", Actor.RUNNING, asociado));
        notifyAll();
    }

    public synchronized void solicitarMantenimiento(Operario operario){
        while ( Simulation.getInstance().isRunning() && !estado.puedeMantenimiento()) {
            try {
            	setChanged();
                this.notifyObservers(new AmbulanceStateMessage("No se puede realizar el mantenimiento en este momento", Actor.PENDING, operario));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado.solicitarMantenimiento();
        setChanged();
        notifyObservers(new AmbulanceStateMessage("Se solicito mantenimiento", Actor.RUNNING, operario));
        notifyAll();
    }

    public synchronized void retornoAutomatico(){
        this.estado.retornoAutomatico();
        setChanged();
        notifyObservers( new AmbulanceStateMessage("Ambulancia en retorno automatico", Actor.RUNNING) );
        notifyAll();
    }

    public synchronized void setEstado(EstadoAbstracto estado) {
        this.estado = estado;
        notifyAll();
    }
    
    public synchronized EstadoAbstracto getEstado() 
    {
    	return this.estado;
    }
}
