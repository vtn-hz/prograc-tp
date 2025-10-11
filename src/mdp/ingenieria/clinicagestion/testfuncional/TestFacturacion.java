package mdp.ingenieria.clinicagestion.testfuncional;

import java.util.ArrayList;
import java.util.List;

import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.clinica.habitacion.HabitacionPrivada;
import mdp.ingenieria.clinicagestion.model.clinica.habitacion.HabitacionTerapiaIntensiva;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.MedicoFactory;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteMayor;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.model.persona.paciente.registro.RegistroPaciente;

public class TestFacturacion {
    
    private static List<Factura> todasLasFacturas = new ArrayList<>();
    
    public static void ejecutarTests() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           TESTING SISTEMA DE FACTURACIÓN                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        MedicoFactory factory = new MedicoFactory();
        
        // Crear médicos que se usarán en los tests
        IMedico medicoClinico1 = factory.create(
            1234, "Dr. Juan Pérez", "12345678", "223-4567890", 
            "Mar del Plata", "Calle Falsa 123", MedicoFactory.MEDICO_CLINICO
        );
        
        IMedico medicoCirujano1 = factory.create(
            5678, "Dra. María González", "87654321", "223-1234567",
            "Mar del Plata", "Av. Colón 456", MedicoFactory.MEDICO_CIRUJANO,
            MedicoFactory.CONTRATACION_PERMANENTE
        );
        
        IMedico medicoPediatra1 = factory.create(
            2345, "Dr. Carlos López", "11223344", "223-9876543",
            "Mar del Plata", "Calle Rivadavia 789", MedicoFactory.MEDICO_PEDIATRA,
            MedicoFactory.CONTRATACION_RESIDENTE, MedicoFactory.POSTGRADO_MAGISTER
        );
        
        
        
        IMedico medicoDoctorado = factory.create(
            9999, "Dra. Laura Martínez", "22222222", "223-2222222",
            "Mar del Plata", "Calle Libertad 100", MedicoFactory.MEDICO_CIRUJANO,
            MedicoFactory.CONTRATACION_PERMANENTE, MedicoFactory.POSTGRADO_DOCTORADO
        );
        
        // Crear habitaciones con diferentes costos
        Habitacion habPrivada1 = new HabitacionPrivada(5000.0, 2000.0);
        Habitacion habPrivada2 = new HabitacionPrivada(5000.0, 2000.0);
        Habitacion habTerapiaIntensiva = new HabitacionTerapiaIntensiva(10000.0, 1.5);
        
        // ============================================================
        // PACIENTE 1: Juan Carlos Rodríguez - DOS INGRESOS
        // ============================================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  PACIENTE 1: Juan Carlos Rodríguez - Múltiples Ingresos    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Domicilio domicilio1 = new Domicilio("223-555-1001", "Mar del Plata", "Av. Colón 1234");
        Paciente paciente1 = new PacienteMayor(
            "Juan Carlos Rodríguez", "30123456", domicilio1, 1001
        );
        RegistroPaciente registroPaciente1 = new RegistroPaciente(paciente1);
        
        // PRIMER INGRESO - Consulta ambulatoria (sin habitación)
        System.out.println("┌─ INGRESO 1: Consulta Ambulatoria (sin habitación) ───────┐");
        registroPaciente1.addRegistroIngreso();
        registroPaciente1.setDias(1);
        registroPaciente1.addAtendidoPor(medicoClinico1);
        
        System.out.println("✓ Paciente: " + paciente1.getNyA());
        System.out.println("✓ Historia Clínica: " + paciente1.getNroHistoriaClinica());
        System.out.println("✓ Días: " + registroPaciente1.getRegistroIngresoActual().getDias());
        System.out.println("✓ Habitación: Ninguna");
        System.out.println("✓ Médicos: " + medicoClinico1.getNyA());
        
        registroPaciente1.finalizarIngreso();
        agregarFactura(registroPaciente1.getFactura());
        System.out.println("✓ Ingreso 1 finalizado\n");
        
        // SEGUNDO INGRESO - Internación con habitación privada
        System.out.println("┌─ INGRESO 2: Internación en Habitación Privada ───────────┐");
        registroPaciente1.addRegistroIngreso();
        registroPaciente1.setDias(4);
        registroPaciente1.setHabitacion(habPrivada1);
        registroPaciente1.addAtendidoPor(medicoCirujano1);
        registroPaciente1.addAtendidoPor(medicoClinico1);
        
        System.out.println("✓ Días: " + registroPaciente1.getRegistroIngresoActual().getDias());
        System.out.println("✓ Habitación: " + habPrivada1.getTipoHabitacion());
        System.out.println("✓ Costo habitación: $" + habPrivada1.getCostoTotal(4));
        System.out.println("✓ Médicos: " + medicoCirujano1.getNyA() + ", " + medicoClinico1.getNyA());
        
        registroPaciente1.finalizarIngreso();
        agregarFactura(registroPaciente1.getFactura());
        System.out.println("✓ Ingreso 2 finalizado");
        System.out.println("✓ Total de ingresos: " + registroPaciente1.getRegistroIngresos().size() + "\n");
        
        // ============================================================
        // PACIENTE 2: Ana María López - HABITACIÓN PRIVADA EXTENDIDA
        // ============================================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  PACIENTE 2: Ana María López - Internación Extendida       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Domicilio domicilio2 = new Domicilio("223-555-1002", "Mar del Plata", "Calle San Martín 567");
        Paciente paciente2 = new PacienteMayor(
            "Ana María López", "27654321", domicilio2, 1002
        );
        RegistroPaciente registroPaciente2 = new RegistroPaciente(paciente2);
        
        System.out.println("┌─ INGRESO: Habitación Privada 7 días ─────────────────────┐");
        registroPaciente2.addRegistroIngreso();
        registroPaciente2.setDias(7);
        registroPaciente2.setHabitacion(habPrivada2);
        registroPaciente2.addAtendidoPor(medicoPediatra1);
        registroPaciente2.addAtendidoPor(medicoClinico1);
        
        System.out.println("✓ Paciente: " + paciente2.getNyA());
        System.out.println("✓ Historia Clínica: " + paciente2.getNroHistoriaClinica());
        System.out.println("✓ Días: " + registroPaciente2.getRegistroIngresoActual().getDias());
        System.out.println("✓ Habitación: " + habPrivada2.getTipoHabitacion());
        System.out.println("✓ Costo habitación (>6 días, x2): $" + habPrivada2.getCostoTotal(7));
        System.out.println("✓ Médicos: " + medicoPediatra1.getNyA() + ", " + medicoClinico1.getNyA());
        
        registroPaciente2.finalizarIngreso();
        agregarFactura(registroPaciente2.getFactura());
        System.out.println("✓ Ingreso finalizado\n");
        
        // ============================================================
        // PACIENTE 3: Roberto Fernández - TERAPIA INTENSIVA
        // ============================================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  PACIENTE 3: Roberto Fernández - Terapia Intensiva         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Domicilio domicilio3 = new Domicilio("223-555-1003", "Mar del Plata", "Av. Libertad 999");
        Paciente paciente3 = new PacienteMayor(
            "Roberto Fernández", "33445566", domicilio3, 1003
        );
        RegistroPaciente registroPaciente3 = new RegistroPaciente(paciente3);
        
        // INGRESO 1 - Terapia intensiva corta
        System.out.println("┌─ INGRESO 1: Terapia Intensiva 3 días ────────────────────┐");
        registroPaciente3.addRegistroIngreso();
        registroPaciente3.setDias(3);
        registroPaciente3.setHabitacion(habTerapiaIntensiva);
        registroPaciente3.addAtendidoPor(medicoCirujano1);
        registroPaciente3.addAtendidoPor(medicoDoctorado);
        registroPaciente3.addAtendidoPor(medicoClinico1);
        
        System.out.println("✓ Días: 3");
        System.out.println("✓ Habitación: " + habTerapiaIntensiva.getTipoHabitacion());
        System.out.println("✓ Costo habitación (exponencial 1.5^3): $" + habTerapiaIntensiva.getCostoTotal(3));
        System.out.println("✓ Médicos: 3 especialistas");
        
        registroPaciente3.finalizarIngreso();
        agregarFactura(registroPaciente3.getFactura());
        System.out.println("✓ Ingreso 1 finalizado\n");
        
        // INGRESO 2 - Consulta post-alta
        System.out.println("┌─ INGRESO 2: Control Post-Alta ───────────────────────────┐");
        registroPaciente3.addRegistroIngreso();
        registroPaciente3.setDias(1);
        registroPaciente3.addAtendidoPor(medicoClinico1);
        
        System.out.println("✓ Días: 1");
        System.out.println("✓ Habitación: Ninguna");
        System.out.println("✓ Médico: " + medicoClinico1.getNyA());
        
        registroPaciente3.finalizarIngreso();
        agregarFactura(registroPaciente3.getFactura());
        System.out.println("✓ Ingreso 2 finalizado");
        System.out.println("✓ Total de ingresos: " + registroPaciente3.getRegistroIngresos().size() + "\n");
        
        // ============================================================
        // PACIENTE 4: Carmen Suárez - HABITACIÓN PRIVADA CORTA
        // ============================================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  PACIENTE 4: Carmen Suárez - Hab. Privada Rango Medio      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Habitacion habPrivada3 = new HabitacionPrivada(5000.0, 2000.0);
        Domicilio domicilio4 = new Domicilio("223-555-1004", "Mar del Plata", "Calle Mitre 456");
        Paciente paciente4 = new PacienteMayor(
            "Carmen Suárez", "28987654", domicilio4, 1004
        );
        RegistroPaciente registroPaciente4 = new RegistroPaciente(paciente4);
        
        System.out.println("┌─ INGRESO: Habitación Privada 3 días ─────────────────────┐");
        registroPaciente4.addRegistroIngreso();
        registroPaciente4.setDias(3);
        registroPaciente4.setHabitacion(habPrivada3);
        registroPaciente4.addAtendidoPor(medicoPediatra1);
        
        System.out.println("✓ Paciente: " + paciente4.getNyA());
        System.out.println("✓ Días: 3 (rango 2-5 días, x1.3)");
        System.out.println("✓ Habitación: " + habPrivada3.getTipoHabitacion());
        System.out.println("✓ Costo habitación: $" + habPrivada3.getCostoTotal(3));
        System.out.println("✓ Médico: " + medicoPediatra1.getNyA());
        
        registroPaciente4.finalizarIngreso();
        agregarFactura(registroPaciente4.getFactura());
        System.out.println("✓ Ingreso finalizado\n");
        
        // ============================================================
        // MOSTRAR TODAS LAS FACTURAS GENERADAS
        // ============================================================
        mostrarTodasLasFacturas();
        
        // ============================================================
        // ESTADÍSTICAS FINALES
        // ============================================================
        mostrarEstadisticas(registroPaciente1, registroPaciente2, 
                           registroPaciente3, registroPaciente4);
    }
    
    private static void agregarFactura(Factura factura) {
        if (factura != null) {
            todasLasFacturas.add(factura);
        }
    }
    
    private static void mostrarTodasLasFacturas() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              TODAS LAS FACTURAS GENERADAS                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        if (todasLasFacturas.isEmpty()) {
            System.out.println("⚠ No se generaron facturas (método finalizarIngreso() pendiente)\n");
            return;
        }
        
        for (int i = 0; i < todasLasFacturas.size(); i++) {
            Factura factura = todasLasFacturas.get(i);
            System.out.println("───────────────────────────────────────────────────────────────");
            System.out.println("  FACTURA " + (i + 1) + " de " + todasLasFacturas.size());
            System.out.println("───────────────────────────────────────────────────────────────\n");
            System.out.println(factura.toString());
        }
    }
    
    private static void mostrarEstadisticas(RegistroPaciente... registros) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ESTADÍSTICAS GENERALES                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int totalPacientes = registros.length;
        int totalIngresos = 0;
        int ingresosConHabitacion = 0;
        int ingresosSinHabitacion = 0;
        
        for (RegistroPaciente registro : registros) {
            totalIngresos += registro.getRegistroIngresos().size();
            for (int i = 0; i < registro.getRegistroIngresos().size(); i++) {
                if (registro.getRegistroIngresos().get(i).getHabitacion() != null) {
                    ingresosConHabitacion++;
                } else {
                    ingresosSinHabitacion++;
                }
            }
        }
        
        System.out.println("📊 Resumen del Sistema:");
        System.out.println("   • Total de pacientes procesados: " + totalPacientes);
        System.out.println("   • Total de ingresos registrados: " + totalIngresos);
        System.out.println("   • Ingresos con habitación: " + ingresosConHabitacion);
        System.out.println("   • Ingresos sin habitación (ambulatorios): " + ingresosSinHabitacion);
        System.out.println("   • Total de facturas generadas: " + todasLasFacturas.size());
        
        System.out.println("\n📋 Detalle por Paciente:");
        for (int i = 0; i < registros.length; i++) {
            RegistroPaciente registro = registros[i];
            System.out.println("   " + (i+1) + ". " + registro.getPaciente().getNyA() + 
                             " (HC: " + registro.getPaciente().getNroHistoriaClinica() + ")");
            System.out.println("      └─ Ingresos: " + registro.getRegistroIngresos().size());
        }
        
        System.out.println("\n✅ PRUEBAS COMPLETADAS EXITOSAMENTE");
    }
}