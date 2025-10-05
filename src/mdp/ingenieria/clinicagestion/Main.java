package mdp.ingenieria.clinicagestion;

import mdp.ingenieria.clinicagestion.testfuncional.TestDecorators;
import mdp.ingenieria.clinicagestion.testfuncional.TestFacturacion;
import mdp.ingenieria.clinicagestion.testfuncional.TestRegistroMedico;

public class Main {

    private static TestRegistroMedico TestFacturacion;

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║         SISTEMA DE GESTIÓN DE CLÍNICA - TESTS         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        // Ejecutar tests de decoradores
        // TestDecorators.ejecutarTests();
        
        // System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Ejecutar tests de facturación
        // TestFacturacion.ejecutarTests();

        // Ejecutar tests de registro
        // TestFacturacion.ejecutarTests();
    }
}