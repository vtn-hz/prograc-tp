package mdp.ingenieria.clinicagestion.model.persona.paciente.factura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una factura emitida a un paciente por los servicios prestados en la clínica.
 */
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

    /**
     * Constructor de una nueva factura vacía con número asignado automáticamente.
     * <b>post:</b> se inicializa una factura con número único y lista vacía de ítems
     */
    public Factura() {
        this.nroFactura = Factura.nroFacturaActual++;
        this.items = new ArrayList<>();
    }

    /**
     * Contructor de una factura con la información completa
     * <b>pre:</b> las fechas no deben ser nulas, el nombre del paciente no debe estar vacío
     * y la lista de ítems debe estar correctamente inicializada <br>
     * <b>post:</b> se crea una factura con los datos especificados y número único
     *
     * @param nombrePaciente        nombre del paciente
     * @param fechaIngreso          fecha y hora de ingreso del paciente
     * @param fechaEgreso           fecha y hora de egreso del paciente
     * @param utilizoHabitacion     indica si se utilizó habitación
     * @param cantidadDias          cantidad total de días de internación
     * @param habitacionDias        cantidad de días en habitación
     * @param tipoHabitacion        tipo de habitación utilizada
     * @param costoHabitacion       costo total del uso de la habitación
     * @param items                 lista de ítems incluidos en la factura
     */
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
    /** @return número identificador único de la factura */
    public int getNroFactura() {
        return nroFactura;
    }

    /** @return nombre del paciente asociado a la factura */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /** @return fecha y hora de ingreso del paciente */
    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    /** @return fecha y hora de egreso del paciente */
    public LocalDateTime getFechaEgreso() {
        return fechaEgreso;
    }

    /** @return cantidad total de días de internación */
    public int getCantidadDias() {
        return cantidadDias;
    }

    /** @return cantidad de días de uso de la habitación */
    public int getHabitacionDias() {
        return habitacionDias;
    }

    /** @return true si el paciente utilizó habitación durante su internación, false en caso contrario */
    public boolean getUtilizoHabitacion() {
		return utilizoHabitacion;
	}

    /** @return tipo de habitación utilizada */
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

    /** @return costo total de la habitación */
    public double getCostoHabitacion() {
        return costoHabitacion;
    }

    /** @return lista de ítems incluidos en la factura */
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

    /**
     * Agrega un nuevo ítem a la factura.
     *
     * <b>pre:</b> el ítem no debe ser nulo <br>
     * <b>post:</b> el ítem queda registrado en la lista de ítems facturados
     *
     * @param item objeto FacturaItem a agregar
     */
    public void addItem(FacturaItem item) {
        this.items.add(item);
    }

    /**
     * Calcula el monto total de la factura, sumando el costo de habitación
     *
     * <b>pre:</b> la factura debe estar correctamente inicializada y los ítems definidos <br>
     * <b>post:</b> devuelve el valor total a pagar
     *
     * @return monto total de la factura
     */
    private double calcularTotal() {
        double total = costoHabitacion;
        for (FacturaItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Devuelve una representación textual detallada de la factura.
     * <b>post:</b> devuelve una cadena formateada con toda la información relevante
     *
     * @return descripción en texto de la factura
     */
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
