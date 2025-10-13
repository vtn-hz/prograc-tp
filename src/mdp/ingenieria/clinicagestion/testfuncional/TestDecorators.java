package mdp.ingenieria.clinicagestion.testfuncional;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.MedicoFactory;

public class TestDecorators {
    
    private static final double DELTA = 0.01;
    
    public static void ejecutarTests() {
        MedicoFactory factory = new MedicoFactory();
        
        System.out.println("=== TESTING MEDICO FACTORY CON ASSERTS ===\n");
        
        // Test 1: Médico Clínico sin decoradores
        System.out.println("--- Test 1: Médico Clínico sin decoradores ---");
        IMedico medicoClinico = factory.create(
            1234, "Dr. Juan Pérez", "12345678", "223-4567890", 
            "Mar del Plata", "Calle Falsa 123", MedicoFactory.MEDICO_CLINICO
        );
        double expectedClinico = 21000.0;
        System.out.println("Esperado: " + expectedClinico);
        System.out.println("Obtenido: " + medicoClinico.getHonorario());
        assert Math.abs(medicoClinico.getHonorario() - expectedClinico) < DELTA : 
            "Error: Clínico básico. Esperado: " + expectedClinico;
        System.out.println("✓ Test 1 PASADO\n");
        
        // Test 2: Médico Cirujano con Contratación Permanente
        System.out.println("--- Test 2: Médico Cirujano con Contratación Permanente ---");
        IMedico medicoCirujano = factory.create(
            123123, "Dra. María González", "87654321", "223-1234567",
            "Mar del Plata", "Av. Colón 456", MedicoFactory.MEDICO_CIRUJANO,
            MedicoFactory.CONTRATACION_PERMANENTE
        );
        double expectedCirujano = 24200.0;
        System.out.println("Esperado: " + expectedCirujano);
        System.out.println("Obtenido: " + medicoCirujano.getHonorario());
        assert Math.abs(medicoCirujano.getHonorario() - expectedCirujano) < DELTA;
        System.out.println("✓ Test 2 PASADO\n");
        
        // Test 3: Médico Pediatra con Residente y Magister
        System.out.println("--- Test 3: Médico Pediatra con Residente y Magister ---");
        IMedico medicoPediatra = factory.create(
            2345, "Dr. Carlos López", "11223344", "223-9876543",
            "Mar del Plata", "Calle Rivadavia 789", MedicoFactory.MEDICO_PEDIATRA,
            MedicoFactory.CONTRATACION_RESIDENTE, MedicoFactory.POSTGRADO_MAGISTER
        );
        double expectedPediatra = 23593.5;
        System.out.println("Esperado: " + expectedPediatra);
        System.out.println("Obtenido: " + medicoPediatra.getHonorario());
        assert Math.abs(medicoPediatra.getHonorario() - expectedPediatra) < DELTA;
        System.out.println("✓ Test 3 PASADO\n");
        
        // Test 4: Médico Clínico con Permanente y Doctorado
        System.out.println("--- Test 4: Médico Clínico con Permanente y Doctorado ---");
        IMedico medicoDoctorado = factory.create(
            134123442, "Dra. Ana Martínez", "55667788", "223-5551234",
            "Mar del Plata", "Calle San Martín 321", MedicoFactory.MEDICO_CLINICO,
            MedicoFactory.CONTRATACION_PERMANENTE, MedicoFactory.POSTGRADO_DOCTORADO
        );
        double expectedDoctorado = 25410.0;
        System.out.println("Esperado: " + expectedDoctorado);
        System.out.println("Obtenido: " + medicoDoctorado.getHonorario());
        assert Math.abs(medicoDoctorado.getHonorario() - expectedDoctorado) < DELTA;
        System.out.println("✓ Test 4 PASADO\n");
        
        // Test 5: Médico Cirujano con Residente y Magister
        System.out.println("--- Test 5: Médico Cirujano con Residente y Magister ---");
        IMedico medicoCirujanoMagister = factory.create(
            1234124334, "Dr. Roberto Sánchez", "99887766", "223-4445556",
            "Mar del Plata", "Calle Mitre 555", MedicoFactory.MEDICO_CIRUJANO,
            MedicoFactory.CONTRATACION_RESIDENTE, MedicoFactory.POSTGRADO_MAGISTER
        );
        double expectedCirujanoMagister = 24255.0;
        System.out.println("Esperado: " + expectedCirujanoMagister);
        System.out.println("Obtenido: " + medicoCirujanoMagister.getHonorario());
        assert Math.abs(medicoCirujanoMagister.getHonorario() - expectedCirujanoMagister) < DELTA;
        System.out.println("✓ Test 5 PASADO\n");
        
        // Test 6: Médico Pediatra con Permanente y Doctorado
        System.out.println("--- Test 6: Médico Pediatra con Permanente y Doctorado ---");
        IMedico medicoPediatraDoctorado = factory.create(
            123413413, "Dra. Laura Fernández", "44556677", "223-7778889",
            "Mar del Plata", "Av. Libertad 888", MedicoFactory.MEDICO_PEDIATRA,
            MedicoFactory.CONTRATACION_PERMANENTE, MedicoFactory.POSTGRADO_DOCTORADO
        );
        double expectedPediatraDoctorado = 25894.0;
        System.out.println("Esperado: " + expectedPediatraDoctorado);
        System.out.println("Obtenido: " + medicoPediatraDoctorado.getHonorario());
        assert Math.abs(medicoPediatraDoctorado.getHonorario() - expectedPediatraDoctorado) < DELTA;
        System.out.println("✓ Test 6 PASADO\n");
        
        System.out.println("=== TODOS LOS TESTS PASARON EXITOSAMENTE ===");
        
        System.out.println("\n=== RESUMEN DE HONORARIOS ===");
        System.out.println("Clínico básico: $" + medicoClinico.getHonorario());
        System.out.println("Cirujano + Permanente: $" + medicoCirujano.getHonorario());
        System.out.println("Pediatra + Residente + Magister: $" + medicoPediatra.getHonorario());
        System.out.println("Clínico + Permanente + Doctorado: $" + medicoDoctorado.getHonorario());
        System.out.println("Cirujano + Residente + Magister: $" + medicoCirujanoMagister.getHonorario());
        System.out.println("Pediatra + Permanente + Doctorado: $" + medicoPediatraDoctorado.getHonorario());
    }
}