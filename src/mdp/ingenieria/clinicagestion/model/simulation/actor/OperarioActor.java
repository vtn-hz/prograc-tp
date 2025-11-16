package mdp.ingenieria.clinicagestion.model.simulation.actor;

import java.util.Random;

import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

public class OperarioActor extends Actor {

    private static final int SOLICITAR_MANTENIMIENTO = 0;

    private static final int AVAILABLE_ACTION_AMOUNT = 1;

    private Random random;

    private Operario operario;

    private int interactionCount;

    /**
     * Crea un OperarioActor.
     *
     * <b>pre:</b> interactionCount >= 0 y operario no debe ser nulo <br>
     * <b>post:</b> el actor queda listo para ejecutar solicitudes de mantenimiento
     *
     * @param interactionCount cantidad de interacciones a ejecutar
     * @param operario operario que realiza las solicitudes
     */
    public OperarioActor(int interactionCount, Operario operario) {
        super();
        this.operario = operario;
        this.interactionCount = interactionCount;
        this.random = new Random();
    }

    /**
     * Ciclo principal del actor: ejecuta tareas mientras queden interacciones
     * pendientes y la simulación siga activa.
     *
     * <b>post:</b> al terminar, notifica a la simulación que este actor finalizó
     */
    @Override
    public void run() {
        while (this.interactionCount > 0 && Simulation.getInstance().isRunning())
        {
            this.runTask();
            this.interactionCount--;
        }
        
        Simulation.getInstance().temporalThreadFinalized();
    }

    /**
     * Ejecuta una acción de mantenimiento seleccionada aleatoriamente.
     *
     * <b>pre:</b> la simulación debe estar en ejecución <br>
     * <b>post:</b> el operario realiza la solicitud y se simula el tiempo asociado
     */
    @Override
    public void runTask() {
        int actionSelected = this.random.nextInt(AVAILABLE_ACTION_AMOUNT);

        ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
        
        switch(actionSelected)
        {
            case SOLICITAR_MANTENIMIENTO: operario.solicitaMantenimiento(); break;
        }
        
        ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );
    }

}