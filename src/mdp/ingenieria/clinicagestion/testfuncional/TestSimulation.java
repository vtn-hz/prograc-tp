package mdp.ingenieria.clinicagestion.testfuncional;

import mdp.ingenieria.clinicagestion.model.data.ActorDTO;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;

public class TestSimulation {

    public static void ejecutarTest() {
        System.out.println("--- INICIANDO TEST DE SIMULACIÓN COMPLETA ---");

        // --- 1. Crear los DTOs de actores asociados ---
        ActorDTO[] asociados = new ActorDTO[] {
            new ActorDTO("Juan Pérez", "12345678", "2231111111", "Mar del Plata", "Calle 1 100", 3),
            new ActorDTO("Ana Gómez", "23456789", "2232222222", "Mar del Plata", "Calle 2 200", 2)
        };

        // --- 2. Crear el DTO del operario ---
        ActorDTO operario = new ActorDTO("Carlos López", "34567890", "2233333333", "Mar del Plata", "Calle 3 300", 4);

        // --- 3. Obtener la instancia de simulación ---
        Simulation simulation = Simulation.getInstance();

        // --- 4. Iniciar la simulación ---
        simulation.start(asociados, operario);

        System.out.println(">>> Simulación iniciada. Esperando finalización de actores...");

        // --- 5. Esperar hasta que termine la simulación ---
        while (simulation.isRunning()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // --- 6. Verificación final ---
        if (!simulation.isRunning() && !simulation.hasTemporalActorsWorking()) {
            System.out.println("✅ TEST EXITOSO: todos los actores completaron sus tareas y la simulación finalizó correctamente.");
        } else {
            System.err.println("❌ TEST FALLIDO: la simulación no finalizó correctamente.");
        }

        System.out.println("--- FIN DEL TEST DE SIMULACIÓN ---");
    }

    // Método main para ejecutarlo directamente
    public static void main(String[] args) {
        ejecutarTest();
    }
}
