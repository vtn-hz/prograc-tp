package mdp.ingenieria.clinicagestion;

import java.time.LocalDateTime;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import java.util.ArrayList;
import mdp.ingenieria.clinicagestion.model.persona.medico.registro.MedicoConsulta;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;

// Esta clase se encargara de imprimir facturas y reportes

public class Printer {

    public static void printFactura(Factura factura) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                     FACTURA CLÍNICA                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println(factura);
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
    }

    public static void printReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, IMedico medico, ArrayList<MedicoConsulta> consultas) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                     REPORTE MÉDICO                    ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println("Reporte de consultas para el médico: " + medico.getNyA());
        System.out.println("Período: " + fechaInicio + " a " + fechaFin);
        System.out.println("Total de consultas: " + consultas.size());
        System.out.println("Detalles de las consultas:");
        for (MedicoConsulta consulta : consultas) {
            System.out.println(consulta);
        }
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
    }     
}