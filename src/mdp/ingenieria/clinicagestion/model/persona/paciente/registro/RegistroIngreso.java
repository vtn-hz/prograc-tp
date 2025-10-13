package mdp.ingenieria.clinicagestion.model.persona.paciente.registro;

import java.time.LocalDateTime;
import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.FacturaFactory;
/**
 * Representa el ingreso de un paciente a la clínica.
 */
public class RegistroIngreso {
	
    private LocalDateTime fechaIngreso;
    
    private int dias;
    
    private Habitacion habitacion;
    
    private ArrayList<IMedico> atendidoPor;
    
    private Factura factura;
    
    private FacturaFactory facturaFactory;

    /**
     * Contructor de un nuevo registro de ingreso con fecha actual y estructuras inicializadas.
     * <b>post:</b> se crea un ingreso con informacion predeterminada
     */
    public RegistroIngreso() {
        this.fechaIngreso = LocalDateTime.now();
        this.atendidoPor = new ArrayList<>();
        this.dias = 0;
        this.factura = null;
        this.facturaFactory = new FacturaFactory(); // maybe agregacion
    }

    /**
     * Retorna la fecha y hora de inicio del ingreso.
     * <b>post:</b> devuelve la fecha registrada
     *
     * @return fecha de ingreso
     */
    public LocalDateTime getFechaIngreso() {
    	return this.fechaIngreso;
    }

    /**
     * Establece la cantidad de días del ingreso.
     *
     * <b>pre:</b> dias debe ser mayor o igual que cero <br>
     * <b>post:</b> actualiza la cantidad de días del ingreso
     *
     * @param dias cantidad de días
     */
    public void setDias(int dias) {
        this.dias = dias;
    }

    /**
     * Retorna la cantidad de días del ingreso.
     * <b>post:</b> devuelve el valor actual de días
     *
     * @return cantidad de días
     */

    public int getDias() {
    	return this.dias;
    }

    /**
     * Asigna la habitación utilizada durante el ingreso.
     * <b>post:</b> actualiza la habitación asociada al ingreso
     *
     * @param habitacion habitación asignada (puede ser null)
     */
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * Retorna la habitación asociada al ingreso.
     * <b>post:</b> devuelve la habitación o {@code null} si no hubo
     *
     * @return habitación utilizada o null.
     */
    public Habitacion getHabitacion() {
    	return this.habitacion;
    }

    /**
     * Agrega un médico al listado de profesionales que atendieron al paciente.
     *
     * <b>pre:</b> medico  debe ser distinto de null <br>
     * <b>post:</b> el médico queda registrado en la lista de atención
     *
     * @param medico profesional que atendió al paciente
     */
    public void addAtendidoPor(IMedico medico) {
        atendidoPor.add(medico);
    }

    /**
     * Retorna la lista de médicos que atendieron al paciente.
     * <b>post:</b> devuelve la referencia a la lista interna
     *
     * @return lista de médicos que atendieron
     */
    public ArrayList<IMedico> getAtendidoPor() {
        return atendidoPor;
    }

    /**
     * Finaliza el ingreso generando la factura correspondiente utilizando la clase FacturaFactory
     *
     * <b>pre:</b> registroPaciente ndebe ser distinto de null; debe poseer un RegistroIngreso actual coherente con este objeto <br>
     * <b>post:</b> se crea y asocia la factura al ingreso
     *
     * @param registroPaciente registro del paciente del cual se obtendrá la información de facturación
     */
    public void finalizarIngreso( RegistroPaciente registroPaciente ) {
    	this.factura = this.facturaFactory.create( registroPaciente );
    }

    /**
     * Retorna la factura generada al finalizar el ingreso.
     *
     * <b>pre:</b> se debe haber llamado previamente al metodo finalizarIngreso para generar la factura<br>
     * <b>post:</b> devuelve la factura asociada, o null si aún no fue generada
     *
     * @return factura asociada al ingreso (null si aún no fue generada)
     */
    public Factura getFactura() {
        return this.factura;
    }
}