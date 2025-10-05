package mdp.ingenieria.clinicagestion.testfuncional;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoCirujano;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.MedicoConsulta;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.RegistroMedico;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestRegistroMedico {

    private static final double DELTA = 0.01;

    public static void ejecutarTests() {
        System.out.println("=== TESTING REGISTRO MÉDICO CON ASSERTS ===\n");

        // Crear médico y registro
        Domicilio domicilio = new Domicilio("123456789", "Buenos Aires", "Calle Falsa 123");
        MedicoCirujano medico = new MedicoCirujano(1234, "Dr. López", "12345678", domicilio);
        RegistroMedico registro = new RegistroMedico(medico);

        System.out.println("--- Test 1: Registro y filtrado de consultas por fecha ---");

        // Agregar consultas
        registro.addConsulta(LocalDateTime.of(2025, 10, 1, 10, 0), "Juan Pérez");     // fuera del rango
        registro.addConsulta(LocalDateTime.of(2025, 10, 3, 11, 0), "Sofía Martínez"); // dentro del rango
        registro.addConsulta(LocalDateTime.of(2025, 10, 5, 15, 0), "Ana Gómez");      // dentro del rango
        registro.addConsulta(LocalDateTime.of(2025, 10, 8, 9, 0), "Niño Ramírez");    // fuera del rango

        // Definir rango de fechas
        LocalDateTime inicio = LocalDateTime.of(2025, 10, 2, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 10, 7, 23, 59);

        ArrayList<MedicoConsulta> rango = registro.getConsultasEfectuadasByFecha(inicio, fin);

        // Asserts
        int expectedTotal = 4;
        int expectedRango = 2;

        System.out.println("Consultas totales esperadas: " + expectedTotal);
        System.out.println("Consultas en rango esperadas: " + expectedRango);

        assert registro.getConsultasEfectuadas().size() == expectedTotal :
                "Error: cantidad total de consultas incorrecta.";

        assert rango.size() == expectedRango :
                "Error: cantidad de consultas dentro del rango incorrecta.";

        // Verificar nombres de pacientes en rango
        assert rango.get(0).getNombrePaciente().equals("Sofía Martínez") :
                "Error: primer paciente incorrecto en rango.";
        assert rango.get(1).getNombrePaciente().equals("Ana Gómez") :
                "Error: segundo paciente incorrecto en rango.";

        System.out.println("✓ Test 1 PASADO\n");

        System.out.println("=== TODOS LOS TESTS DE REGISTRO PASARON EXITOSAMENTE ===\n");

        System.out.println("=== RESUMEN DE CONSULTAS ===");
        System.out.println("Total de consultas registradas: " + registro.getConsultasEfectuadas().size());
        System.out.println("Consultas en rango (" + inicio.toLocalDate() + " a " + fin.toLocalDate() + "): " + rango.size());
        for (MedicoConsulta c : rango) {
            System.out.println("- " + c.getNombrePaciente() + " (" + c.getFecha() + ") Honorario: " + c.getHonorario());
        }
    }
}
