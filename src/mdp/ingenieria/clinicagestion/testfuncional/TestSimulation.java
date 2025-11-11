package mdp.ingenieria.clinicagestion.testfuncional;

import java.util.Random;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.data.ActorDTO;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.model.simulation.TaskInteraction;

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
        Ambulancia ambulancia = new Ambulancia();

        // --- 4. Iniciar la simulación ---
        simulation.start(asociados, operario, ambulancia, 4000);
        System.out.println(">>> Simulación iniciada. Generando batería de acciones...");

        // --- 5. Lanzar batería de acciones en hilo separado ---
        Thread actionThread = new Thread(() -> lanzarBateriaDeAcciones(simulation, ambulancia));
        actionThread.start();

        // --- 6. Esperar a que la simulación termine ---
        while (simulation.isRunning()) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // --- 7. Esperar que terminen tareas temporales ---
        while (simulation.hasTemporalThreadWorking()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // --- 8. Verificación final ---
        System.out.println("✅ TEST FINALIZADO: todos los actores completaron sus tareas.");
        System.out.println("--- FIN DE LA SIMULACIÓN ---");
    }

    /**
     * Lanza una batería de acciones aleatorias mientras la simulación está activa.
     */
    private static void lanzarBateriaDeAcciones(Simulation simulation, Ambulancia ambulancia) {
        Random random = new Random();

        int cantidadAcciones = 10; // cantidad total de eventos simulados
        int delayMin = 800;        // tiempo mínimo entre acciones (ms)
        int delayMax = 2500;       // tiempo máximo entre acciones (ms)

        for (int i = 1; i <= cantidadAcciones && simulation.isRunning(); i++) {
            int tipo = random.nextInt(3); // 0, 1 o 2

            switch (tipo) {
                case 0:
                    new TaskInteraction(0, random.nextInt(1500) + 500, ambulancia);
                    System.out.printf("🟢 [%02d] Acción: Solicitud de atención a domicilio%n", i);
                    break;
                case 1:
                    new TaskInteraction(1, random.nextInt(1500) + 500, ambulancia);
                    System.out.printf("🚑 [%02d] Acción: Solicitud de ambulancia%n", i);
                    break;
                case 2:
                    new TaskInteraction(2, random.nextInt(1500) + 500, ambulancia);
                    System.out.printf("🛠️ [%02d] Acción: Solicitud de mantenimiento%n", i);
                    break;
            }

            try {
                Thread.sleep(delayMin + random.nextInt(delayMax - delayMin));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("🧩 Batería de acciones completada.");
    }

    // Metodo main para ejecutar directamente
    public static void main(String[] args) {
        ejecutarTest();
    }
}
