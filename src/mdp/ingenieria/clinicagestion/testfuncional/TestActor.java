package mdp.ingenieria.clinicagestion.testfuncional;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

// ... (todos tus imports de modelo, actor, etc. van aquí) ...
import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.model.simulation.actor.AmbulanciaActor;
import mdp.ingenieria.clinicagestion.model.simulation.actor.AsociadoActor;
import mdp.ingenieria.clinicagestion.model.simulation.actoraction.AmbulanciaActorAction;
import mdp.ingenieria.clinicagestion.model.simulation.actoraction.AsociadoActorAction;

public class TestActor {

    // Tiempos de simulación
    private static final int TIEMPO_TAREA_ACTOR = 5000; 
    private static final int TIEMPO_RETORNO_AMBULANCIA = 3000; 

    public static void ejecutarTests() {
        System.out.println("--- INICIANDO BATERIA DE TESTS DE ACTORES ---");
        
        AmbulanciaMock ambulanciaCompartida = new AmbulanciaMock();
        testLegibleCon3Hilos(ambulanciaCompartida);

        System.out.println("\n--- BATERIA DE TESTS FINALIZADA ---");
    }

    /**
     * Test Legible: 2 Actores de Trabajo + 1 Hilo de Control.
     */
    private static void testLegibleCon3Hilos(AmbulanciaMock ambulancia) {
        System.out.println("\n--- Test Legible: 2 Actores vs 1 Ambulancia (con Hilo de Control) ---");

        List<Thread> hilosDeTrabajo = new ArrayList<>();
        Asociado asocMaestro = new Asociado("Asoc. Multi", "1-2", null, ambulancia);

        // --- 1. PREPARAMOS LOS 2 ACTORES DE TRABAJO ---

        // Actor 1: Pide TRASLADO
        Queue<AsociadoActorAction> colaTraslado = new LinkedList<>(); // Tu LinkedList original
        colaTraslado.add(new AsociadoActorAction(AsociadoActorAction.SOLICITA_AMBULANCIA, TIEMPO_TAREA_ACTOR));
        AsociadoActor actorTraslado = new AsociadoActor(asocMaestro, colaTraslado);
        actorTraslado.setName("Actor-Traslado-A");
        hilosDeTrabajo.add(actorTraslado);
        
        // Actor 2: Pide DOMICILIO
        Queue<AsociadoActorAction> colaDomicilio = new LinkedList<>(); // Tu LinkedList original
        colaDomicilio.add(new AsociadoActorAction(AsociadoActorAction.SOLICITA_AMBULANCIA_DOMICILIO, TIEMPO_TAREA_ACTOR));
        AsociadoActor actorDomicilio = new AsociadoActor(asocMaestro, colaDomicilio);
        actorDomicilio.setName("Actor-Domicilio-B");
        hilosDeTrabajo.add(actorDomicilio);

        System.out.println("Test Legible: 2 actores de trabajo listos.");


        // --- 2. PREPARAMOS EL HILO DE CONTROL (Tu AmbulanciaActor) ---
        
        AmbulanciaActorAction accionRetorno = new AmbulanciaActorAction(
            AmbulanciaActorAction.RETORNO_AUTOMATICO, 
            TIEMPO_RETORNO_AMBULANCIA
        );
        
        AmbulanciaActor hiloDeControl = new AmbulanciaActor(ambulancia, accionRetorno);
        hiloDeControl.setName("HILO-CONTROL-AMBULANCIA");
        hiloDeControl.setDaemon(true); 

        // --- 3. ¡QUE COMIENCE LA FUNCIÓN! ---

        System.out.println(">>> Arrancando Hilo de Control (AmbulanciaActor)...");
        hiloDeControl.setSimulationRunning(true);
        hiloDeControl.start();

        try { Thread.sleep(50); } catch (InterruptedException e) {}

        System.out.println(">>> Arrancando los 2 Hilos de Trabajo...");
        
        // --- ¡AQUÍ ESTÁ TU CAMBIO! ---
        actorTraslado.setSimulationRunning(true); 
        actorTraslado.start();
        
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        
        // --- ¡Y AQUÍ! ---
        actorDomicilio.setSimulationRunning(true);
        actorDomicilio.start();


        System.out.println(">>> Hilos lanzados. El Test (hilo main) esperará a que los 2 terminen...");

        try {
            for (Thread actor : hilosDeTrabajo) {
                actor.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>> ¡LOS 2 HILOS DE TRABAJO HAN TERMINADO!");
        
        hiloDeControl.setSimulationRunning(false);

        System.out.println("VEREDICTO: [OBSERVACION] Revisa la traza.");
    }
}