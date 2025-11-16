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

    /**
     * Crea una ambulancia en estado Disponible.
     *
     * <b>post:</b> la ambulancia queda lista para aceptar solicitudes
     * de traslado, atención domiciliaria o mantenimiento
     */
    public Ambulancia(){
        this.estado = new EstadoDisponible(this);
    }

    /**
     * Solicita atención domiciliaria para un asociado.
     * Si el estado actual no lo permite, espera hasta que sea posible
     * o la simulación deje de estar en ejecución.
     *
     * <b>pre:</b> asociado no debe ser nulo; la simulación debe estar inicializada <br>
     * <b>post:</b> si se pudo atender, se cambia el estado según la lógica interna
     * y se notifica el inicio de la atención; en caso contrario se registran los intentos
     *
     * @param asociado asociado que solicita la atención domiciliaria
     */
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

    /**
     * Solicita un traslado para el asociado.
     * Si el estado actual no permite traslado, espera hasta que sea posible
     * o mientras la simulación siga corriendo.
     *
     * <b>pre:</b> asociado no debe ser nulo <br>
     * <b>post:</b> si se pudo iniciar el traslado, se actualiza el estado
     * y se notifica un mensaje RUNNING; en caso contrario se notifican mensajes PENDING
     *
     * @param asociado asociado que solicita el traslado
     */
    public synchronized void solicitarTraslado(Asociado asociado){
        assert asociado != null;
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

    /**
     * Solicita una tarea de mantenimiento para la ambulancia.
     * Si el estado no permite mantenimiento, espera hasta que sea posible
     * o mientras la simulación siga corriendo.
     *
     * <b>pre:</b> operario no debe ser nulo <br>
     * <b>post:</b> si se pudo iniciar el mantenimiento, se actualiza el estado
     * y se notifica un mensaje RUNNING; en caso contrario se notifican mensajes PENDING
     *
     * @param operario operario que solicita el mantenimiento
     */

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

    /**
     * Ejecuta un retorno automático de la ambulancia según su estado actual.
     *
     * <b>post:</b> el estado interno se ajusta mediante retornoAutomatico
     * y se notifica un mensaje RUNNING a los observadores
     */
    public synchronized void retornoAutomatico(){
        this.estado.retornoAutomatico();
        setChanged();
        notifyObservers( new AmbulanceStateMessage("Ambulancia en retorno automatico", Actor.RUNNING) );
        notifyAll();
    }

    /**
     * Cambia el estado interno de la ambulancia.
     *
     * <b>pre:</b> estado no debe ser nulo <br>
     * <b>post:</b> la próxima operación utilizará el nuevo estado; se despiertan
     * los hilos en espera
     *
     * @param estado nuevo estado de la ambulancia
     */
    public synchronized void setEstado(EstadoAbstracto estado) {
        this.estado = estado;
        notifyAll();
    }

    /**
     * Devuelve el estado actual de la ambulancia.
     *
     * @return estado interno actual
     */
    public synchronized EstadoAbstracto getEstado() 
    {
    	return this.estado;
    }
}
