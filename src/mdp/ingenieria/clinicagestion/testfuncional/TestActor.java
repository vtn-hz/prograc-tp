package mdp.ingenieria.clinicagestion.testfuncional;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

// ... (todos tus imports de modelo, actor, etc. van aquí) ...
import mdp.ingenieria.clinicagestion.model.AmbulanciaMock;
import mdp.ingenieria.clinicagestion.model.actor.AsociadoActor;
import mdp.ingenieria.clinicagestion.model.actor.AmbulanciaActor;
import mdp.ingenieria.clinicagestion.model.actor.actoraction.AsociadoActorAction;
import mdp.ingenieria.clinicagestion.model.actor.actoraction.AmbulanciaActorAction;
import mdp.ingenieria.clinicagestion.model.persona.Asociado;

public class TestActor {

    // Tiempos de simulación
    // El actor "trabaja" 1 segundo
    private static final int TIEMPO_TAREA_ACTOR = 1000; 
    // El "evento temporal" ocurre cada 3 segundos
    private static final int TIEMPO_RETORNO_AMBULANCIA = 3000; 

    public static void ejecutarTests() {
        System.out.println("--- INICIANDO BATERIA DE TESTS DE ACTORES ---");
        
        // Creamos el único recurso compartido
        AmbulanciaMock ambulanciaCompartida = new AmbulanciaMock();

        // Ejecutamos el test legible
        testLegibleCon3Hilos(ambulanciaCompartida);

        System.out.println("\n--- BATERIA DE TESTS FINALIZADA ---");
    }

    /**
     * Test Legible: 2 Actores de Trabajo + 1 Hilo de Control.
     * VEREDICTO ESPERADO: Una traza limpia y secuencial del wait/notify.
     */
    private static void testLegibleCon3Hilos(AmbulanciaMock ambulancia) {
        System.out.println("\n--- Test Legible: 2 Actores vs 1 Ambulancia (con Hilo de Control) ---");

        List<Thread> hilosDeTrabajo = new ArrayList<>();
        
        // Creamos el "Payload" que usarán los actores
        Asociado asocMaestro = new Asociado("Asoc. Multi", "1-2", null, ambulancia);

        // --- 1. PREPARAMOS LOS 2 ACTORES DE TRABAJO ---

        // Actor 1: Pide TRASLADO (pone state = 2)
        Queue<AsociadoActorAction> colaTraslado = new LinkedList<>();
        colaTraslado.add(new AsociadoActorAction(AsociadoActorAction.SOLICITA_AMBULANCIA, TIEMPO_TAREA_ACTOR));
        AsociadoActor actorTraslado = new AsociadoActor(asocMaestro, colaTraslado);
        actorTraslado.setName("Actor-Traslado-A");
        hilosDeTrabajo.add(actorTraslado);
        
        // Actor 2: Pide DOMICILIO (pone state = 1)
        Queue<AsociadoActorAction> colaDomicilio = new LinkedList<>();
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

        // Damos una pequeña ventaja al Hilo de Control para que arranque
        // (Esto es solo para que los logs salgan más bonitos, no es necesario)
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        System.out.println(">>> Arrancando los 2 Hilos de Trabajo...");
        actorTraslado.start();
        
        // Pequeña pausa para asegurar (casi) que el Actor A tome el lock primero
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        
        actorDomicilio.start();


        System.out.println(">>> Hilos lanzados. El Test (hilo main) esperará a que los 2 terminen...");

        try {
            for (Thread actor : hilosDeTrabajo) {
                // El join espera a que el hilo muera (cosa que hacen por el bug de !isEmpty)
                actor.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>> ¡LOS 2 HILOS DE TRABAJO HAN TERMINADO!");
        
        // Detenemos el hilo de control
        hiloDeControl.setSimulationRunning(false);

        System.out.println("VEREDICTO: [OBSERVACION] Revisa la traza. Deberías ver al Actor-A");
        System.out.println("           tomar la ambulancia, luego al Actor-B esperar, luego al");
        System.out.println("           Hilo-Control liberar, y finalmente al Actor-B tomarla.");
    }

    // ... (Aquí irían los otros tests que comentamos/borramos) ...
}