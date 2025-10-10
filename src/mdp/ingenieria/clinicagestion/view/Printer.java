package mdp.ingenieria.clinicagestion.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mdp.ingenieria.clinicagestion.model.clinica.Clinica;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.*;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

import java.util.ArrayList;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.MedicoConsulta;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;

/**
 * Se encarga de imprimir con consola las correspondientes entidades que interactuan en el sistema
 */
public class Printer {

    /**
     * <b>pre: clinica != null </b>
     * @param clinica
     */
    public static void printClinica(Clinica clinica) {
        printCabecera("CLÍNICA");
        
        System.out.println("Nombre: " + clinica.getNombre());
        System.out.println("Teléfono: " + clinica.getDomicilio().getTelefono());
        System.out.println("Ciudad: " + clinica.getDomicilio().getCiudad());
        System.out.println("Dirección: " + clinica.getDomicilio().getDireccion());
        
        printPie();
    }

    /**
     * <b>pre: factura != null</b>
     * @param factura
     */
    public static void printFactura(Factura factura) {
        printCabecera("FACTURA CLÍNICA");
        
        System.out.println(factura);
        
        printPie();
    }
    
    
    /**
     * <b>pre: salaEsperaPatio != null ; salaEsperaPrivada != null</b>
     * @param salaEsperaPrivada
     * @param salaEsperaPatio
     */
    public static void printSalas(SalaEsperaPrivada salaEsperaPrivada, SalaEsperaPatio salaEsperaPatio) 
    {
        printCabecera("SALAS DE ESPERA");
        
        System.out.println("Sala privada: " + 
        		(salaEsperaPrivada.estaOcupada() 
        		? salaEsperaPrivada.getPaciente().getNyA() : " nadie")
        );
        
        System.out.print("Patio: ");
        StringBuilder sb = new StringBuilder();
        for (Paciente paciente : salaEsperaPatio.getPacientes()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(paciente.getNyA());
        }
        System.out.println(sb);
        
        printPie();
    }
    
    

    /**
     * <b>pre: medico != null ; consultas != null ; fechaInicio != null ; fechaFin != null</b>
     * @param medico
     * @param consultas
     * @param fechaInicio
     * @param fechaFin
     */
    public static void printReporte(IMedico medico, ArrayList<MedicoConsulta> consultas, 
                                   LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        double total = 0;
        
        printCabecera("REPORTE MÉDICO");
        

        
        System.out.println("Reporte de consultas para el médico: " + medico.getNyA());
        System.out.println("Período: " + fechaInicio.format(formatter) + " a " + fechaFin.format(formatter));
        System.out.println("Total de consultas: " + consultas.size());
        System.out.println("\nDetalles de las consultas:");
        for (MedicoConsulta consulta : consultas) {
        	System.out.println("Fecha: " + consulta.getFecha().format(formatter) + ", "
        								 + consulta.getNombrePaciente() + ", "
        								 + String.format("$ %.2f", consulta.getHonorario())
        	);
            total += consulta.getHonorario();
        }
        System.out.println( String.format("$ %.2f", total ));
        
        printPie();
    }
    
    /**
     * Imprime la cabecera con el título centrado
     * @param titulo El título a mostrar
     */
    private static void printCabecera(String titulo) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + centrarTexto(titulo, 75) + "║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Imprime el pie del reporte
     */
    private static void printPie() {
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Centra un texto dentro de un ancho específico
     * @param texto El texto a centrar
     * @param ancho El ancho total disponible
     * @return El texto centrado con espacios
     */
    private static String centrarTexto(String texto, int ancho) {
        int espaciosTotal = ancho - texto.length();
        int espaciosIzq = espaciosTotal / 2;
        int espaciosDer = espaciosTotal - espaciosIzq;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < espaciosIzq; i++) {
            sb.append(" ");
        }
        sb.append(texto);
        for (int i = 0; i < espaciosDer; i++) {
            sb.append(" ");
        }
        
        return sb.toString();
    }
}