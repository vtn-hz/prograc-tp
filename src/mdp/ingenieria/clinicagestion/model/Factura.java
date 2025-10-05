package mdp.ingenieria.clinicagestion.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Factura
{
	private static int nroFacturaActual = 1;
	
    private int nroFactura;
    
    private String nombrePaciente;
    
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaEgreso;
    
    private boolean utilizoHabitacion;
    private int cantidadDias;
    private int habitacionDias;
    private String tipoHabitacion;
    private double costoHabitacion;
    
	private List<FacturaItem> items;

    public Factura() {
        this.nroFactura = Factura.nroFacturaActual++;
        this.items = new ArrayList<>();
    }

    public Factura(
    		String nombrePaciente, 
    		LocalDateTime fechaIngreso, LocalDateTime fechaEgreso, 
    		boolean utilizoHabitacion, int cantidadDias, int habitacionDias, String tipoHabitacion,
    		double costoHabitacion, List<FacturaItem> items
                   
    ) {
        this.nroFactura = Factura.nroFacturaActual++;
        this.nombrePaciente = nombrePaciente;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cantidadDias = cantidadDias;
        this.habitacionDias = habitacionDias;
        this.costoHabitacion = costoHabitacion;
        this.items = items;
    }

    // Getters
    public int getNroFactura() {
        return nroFactura;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDateTime getFechaEgreso() {
        return fechaEgreso;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public int getHabitacionDias() {
        return habitacionDias;
    }
    
    public boolean getUtilizoHabitacion() {
		return utilizoHabitacion;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

    public double getCostoHabitacion() {
        return costoHabitacion;
    }

    public List<FacturaItem> getItems() {
        return items;
    }

    // Setters
    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaEgreso(LocalDateTime fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public void setHabitacionDias(int habitacionDias) {
        this.habitacionDias = habitacionDias;
    }
    
	public void setUtilizoHabitacion(boolean utilizoHabitacion) {
		this.utilizoHabitacion = utilizoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

    public void setCostoHabitacion(double costoHabitacion) {
        this.costoHabitacion = costoHabitacion;
    }

    public void setItems(List<FacturaItem> items) {
        this.items = items;
    }

    // Método para agregar items
    public void addItem(FacturaItem item) {
        this.items.add(item);
    }

    // Método para calcular el total
    private double calcularTotal() {
        double total = costoHabitacion;
        for (FacturaItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("Nº Factura: ").append(nroFactura).append("\n");
        sb.append("Nombre Paciente: ").append(nombrePaciente).append("\n");
        sb.append("Fecha Ingreso: ").append(fechaIngreso.format(formatter)).append("\n");
        sb.append("Fecha Egreso: ").append(fechaEgreso.format(formatter)).append("\n");
        sb.append("Cantidad de días: ").append(cantidadDias).append("\n");
        
        if (this.utilizoHabitacion) {
            sb.append(
                String.format(
                    "%-35s %-15s %10s %8s",
                    "Habitación tipo: " + this.tipoHabitacion,
                    "",
                    "Costo:",
                    String.format("$ %.2f", costoHabitacion)
                )
            ).append("\n");
        }
        
        sb.append("\nConsultas Médicas:\n\n");
        
        for (FacturaItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        
        sb.append("\n");
        sb.append(String.format("%-52s %10s %8s", "", "Total:", String.format("$ %.2f", this.calcularTotal())));
        
        return sb.toString();
    }
}
