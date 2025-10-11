package mdp.ingenieria.clinicagestion.testfuncional;

import mdp.ingenieria.clinicagestion.exception.PacienteNoAtendidoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoEncontradoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoIngresadoException;
import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteJoven;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteMayor;
import mdp.ingenieria.clinicagestion.model.persona.paciente.PacienteNino;
import mdp.ingenieria.clinicagestion.service.GestorAtencionPacienteService;

public class TestFuncionalGestorAtencionPaciente {

    public static void ejecutarTests(String[] args) {
        System.out.println("=== INICIO DE PRUEBAS FUNCIONALES ===\n");
        
        testAnunciarPacienteUnico();
        testResolucionConflictoNinoJoven();
        testResolucionConflictoJovenMayor();
        testResolucionConflictoMayorNino();
        testAtenderPaciente();
        testEgresarPacienteEnAtencion();
        testEgresarPacienteEnEspera();
        testGetSigPacienteAtender();
        testPacienteNoEncontrado();
        
        System.out.println("\n=== FIN DE PRUEBAS FUNCIONALES ===");
    }

    public static void testAnunciarPacienteUnico() {
        System.out.println("TEST 1: Anunciar paciente único - Sala Privada vacía");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente nino = crearPacienteNino("Juan Pérez", "12345678", 1);
        
        gestor.anunciar(nino);
        
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        if (salaPrivada.getPaciente() == nino) {
            System.out.println("✓ PASÓ: Niño ingresó correctamente a Sala Privada\n");
        } else {
            System.out.println("✗ FALLÓ: Niño no está en Sala Privada\n");
        }
    }

    private static void testResolucionConflictoNinoJoven() {
        System.out.println("TEST 2: Conflicto Niño-Joven");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente nino = crearPacienteNino("María López", "87654321", 2);
        Paciente joven = crearPacienteJoven("Carlos García", "11223344", 3);
        
        gestor.anunciar(nino);
        gestor.anunciar(joven);
        
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();
        
        boolean ninoEnPrivada = salaPrivada.getPaciente() == nino;
        boolean jovenEnPatio = salaPatio.getPacienteByHistoriaClinica(3) == joven;
        
        if (ninoEnPrivada && jovenEnPatio) {
            System.out.println("✓ PASÓ: Niño queda en Sala Privada, Joven va al Patio\n");
        } else {
            System.out.println("✗ FALLÓ: Resolución incorrecta del conflicto\n");
        }
    }

    private static void testResolucionConflictoJovenMayor() {
        System.out.println("TEST 3: Conflicto Joven-Mayor");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente joven = crearPacienteJoven("Ana Martínez", "55667788", 4);
        Paciente mayor = crearPacienteMayor("Pedro Sánchez", "99887766", 5);
        
        gestor.anunciar(joven);
        gestor.anunciar(mayor);
        
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();
        
        boolean jovenEnPrivada = salaPrivada.getPaciente() == joven;
        boolean mayorEnPatio = salaPatio.getPacienteByHistoriaClinica(5) == mayor;
        
        if (jovenEnPrivada && mayorEnPatio) {
            System.out.println("✓ PASÓ: Joven queda en Sala Privada, Mayor va al Patio\n");
        } else {
            System.out.println("✗ FALLÓ: Resolución incorrecta del conflicto\n");
        }
    }

    private static void testResolucionConflictoMayorNino() {
        System.out.println("TEST 4: Conflicto Mayor-Niño");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente mayor = crearPacienteMayor("Laura Fernández", "44332211", 6);
        Paciente nino = crearPacienteNino("Diego Ruiz", "66778899", 7);
        
        gestor.anunciar(mayor);
        gestor.anunciar(nino);
        
        SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
        SalaEsperaPatio salaPatio = SalaEsperaPatio.getInstance();
        
        boolean mayorEnPrivada = salaPrivada.getPaciente() == mayor;
        boolean ninoEnPatio = salaPatio.getPacienteByHistoriaClinica(7) == nino;
        
        if (mayorEnPrivada && ninoEnPatio) {
            System.out.println("✓ PASÓ: Mayor queda en Sala Privada, Niño va al Patio\n");
        } else {
            System.out.println("✗ FALLÓ: Resolución incorrecta del conflicto\n");
        }
    }

    private static void testAtenderPaciente() {
        System.out.println("TEST 5: Atender paciente");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente paciente = crearPacienteJoven("Luis Torres", "12121212", 8);
        
        gestor.anunciar(paciente);
        
        try {
            gestor.atender(paciente);
            SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
            
            if (salaPrivada.getPaciente() != paciente) {
                System.out.println("✓ PASÓ: Paciente fue retirado de sala de espera y atendido\n");
            } else {
                System.out.println("✗ FALLÓ: Paciente sigue en sala de espera\n");
            }
        } catch (PacienteNoIngresadoException e) {
            System.out.println("✗ FALLÓ: Error al atender paciente - " + e.getMessage() + "\n");
        }
    }

    private static void testEgresarPacienteEnAtencion() {
        System.out.println("TEST 6: Egresar paciente en atención");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente paciente = crearPacienteMayor("Rosa Gómez", "34343434", 9);
        
        gestor.anunciar(paciente);
        
        try {
            gestor.atender(paciente);
            gestor.egresar(paciente);
            System.out.println("✓ PASÓ: Paciente egresado correctamente\n");
        } catch (Exception e) {
            System.out.println("✗ FALLÓ: Error al egresar paciente - " + e.getMessage() + "\n");
        }
    }

    private static void testEgresarPacienteEnEspera() {
        System.out.println("TEST 7: Egresar paciente en espera");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente paciente = crearPacienteNino("Sofia Morales", "56565656", 10);
        
        gestor.anunciar(paciente);
        
        try {
            gestor.egresar(paciente);
            SalaEsperaPrivada salaPrivada = SalaEsperaPrivada.getInstance();
            
            if (salaPrivada.getPaciente() != paciente) {
                System.out.println("✓ PASÓ: Paciente egresado de sala de espera\n");
            } else {
                System.out.println("✗ FALLÓ: Paciente no fue retirado de sala de espera\n");
            }
        } catch (Exception e) {
            System.out.println("✗ FALLÓ: Error al egresar paciente - " + e.getMessage() + "\n");
        }
    }

    private static void testGetSigPacienteAtender() {
        System.out.println("TEST 8: Obtener siguiente paciente a atender");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente paciente1 = crearPacienteJoven("Jorge Díaz", "78787878", 11);
        Paciente paciente2 = crearPacienteMayor("Elena Castro", "90909090", 12);
        
        gestor.anunciar(paciente1);
        gestor.anunciar(paciente2);
        
        Paciente siguiente = gestor.getSigPacienteAtender();
        
        if (siguiente == paciente1) {
            System.out.println("✓ PASÓ: Se obtiene el primer paciente en orden de llegada\n");
        } else {
            System.out.println("✗ FALLÓ: El siguiente paciente no es el correcto\n");
        }
    }

    private static void testPacienteNoEncontrado() {
        System.out.println("TEST 9: Egresar paciente no registrado");
        resetearSalas();
        
        GestorAtencionPacienteService gestor = new GestorAtencionPacienteService();
        Paciente pacienteNoRegistrado = crearPacienteNino("Inexistente", "00000000", 99);
        
        try {
            gestor.egresar(pacienteNoRegistrado);
            System.out.println("✗ FALLÓ: Debería lanzar PacienteNoEncontradoException\n");
        } catch (PacienteNoEncontradoException e) {
            System.out.println("✓ PASÓ: Se lanzó correctamente PacienteNoEncontradoException\n");
        } catch (PacienteNoIngresadoException e) {
            System.out.println("✓ PASÓ: Se lanzó correctamente PacienteNoIngresadoException\n");
        } catch (PacienteNoAtendidoException e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos auxiliares
    private static void resetearSalas() {
        try {
            java.lang.reflect.Field fieldPrivada = SalaEsperaPrivada.class.getDeclaredField("_instancia");
            fieldPrivada.setAccessible(true);
            fieldPrivada.set(null, null);
            
            java.lang.reflect.Field fieldPatio = SalaEsperaPatio.class.getDeclaredField("_instancia");
            fieldPatio.setAccessible(true);
            fieldPatio.set(null, null);
        } catch (Exception e) {
            System.err.println("Error al resetear salas: " + e.getMessage());
        }
    }

    private static Paciente crearPacienteNino(String nombre, String dni, int historiaClinica) {
        Domicilio domicilio = new Domicilio("223-4567890", "Mar del Plata", "Calle Falsa 123");
        return new PacienteNino(nombre, dni, domicilio, historiaClinica);
    }

    private static Paciente crearPacienteJoven(String nombre, String dni, int historiaClinica) {
        Domicilio domicilio = new Domicilio("223-4567890", "Mar del Plata", "Calle Falsa 123");
        return new PacienteJoven(nombre, dni, domicilio, historiaClinica);
    }

    private static Paciente crearPacienteMayor(String nombre, String dni, int historiaClinica) {
        Domicilio domicilio = new Domicilio("223-4567890", "Mar del Plata", "Calle Falsa 123");
        return new PacienteMayor(nombre, dni, domicilio, historiaClinica);
    }
}
