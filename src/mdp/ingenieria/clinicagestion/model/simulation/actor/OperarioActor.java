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

    public OperarioActor(int averageTaskTime, int interactionCount, Operario operario) {
        super(averageTaskTime);
        this.operario = operario;
        this.interactionCount = interactionCount;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (this.interactionCount > 0 && Simulation.getInstance().isRunning())
        {
            this.runTask();
            this.interactionCount--;
        }
        
        Simulation.getInstance().temporalThreadFinalized();
    }

    @Override
    public void runTask() {
        ThreadUtil.simulateTimeMedio( this.getAverageTaskTime() );

        int actionSelected = this.random.nextInt(AVAILABLE_ACTION_AMOUNT);
        assert actionSelected > 0;

        switch(actionSelected)
        {
            case SOLICITAR_MANTENIMIENTO: operario.solicitaMantenimiento(); break;
        }
    }

    /**
     * cuando el usuario interactua con la simulacion, se deberia crear otro OperarioActor con
     * la implementacion actual, ya que los hilos viven hasta que se completen sus tareas.
     */
}