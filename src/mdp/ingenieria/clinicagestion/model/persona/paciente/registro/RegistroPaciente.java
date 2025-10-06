package mdp.ingenieria.clinicagestion.model.persona.paciente.registro;

import java.util.ArrayList;
import java.util.Stack;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
/**
 * Mantiene el historial de ingresos de un Paciente y expone operaciones para gestionar el ingreso actual.
 */
public class RegistroPaciente {
	
    private Paciente paciente;
    
    private Stack<RegistroIngreso> registroIngresos;

    /**
     * Constructor de un registro para el paciente indicado.
     *
     * <b>pre:</b> paciente debe ser distinto de null <br>
     * <b>post:</b> se inicializa el registro con la pila de ingresos vacía
     *
     * @param paciente instancia del paciente a registrar
     */
    public RegistroPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.registroIngresos = new Stack<>();
    }

    /**
     * Inicia un nuevo ingreso para el paciente y lo establece como ingreso actual.
     *
     * <b>pre:</b> no hay requisitos; si existe un ingreso previo, permanece en el historial <br>
     * <b>post:</b> se apila un RegistroIngreso nuevo en el stack registroIngresos
     */
    public void addRegistroIngreso(){
        this.registroIngresos.push( new RegistroIngreso() );
    }

    /**
     * Retorna el ingreso actual (tope del stack).
     *
     * <b>pre:</b> debe haberse invocado previamente addRegistroIngreso() al menos una vez <br>
     * <b>post:</b> devuelve la referencia al ingreso activo
     *
     * @return ingreso actual del paciente
     * @throws java.util.EmptyStackException si no hay ingresos en la pila
     */
    public RegistroIngreso getRegistroIngresoActual(){
        return this.registroIngresos.peek();
    }

    /**
     * Retorna la pila completa de ingresos del paciente.
     * <b>post:</b> devuelve la referencia a la pila
     *
     * @return pila de ingresos del paciente
     */
    public  Stack<RegistroIngreso>  getRegistroIngresos(){
        return this.registroIngresos;
    }

    /**
     * Retorna el paciente asociado a este registro.
     * <b>post:</b> devuelve el paciente asociado
     *
     * @return Paciente vinculado
     */
    public Paciente getPaciente(){
        return this.paciente;
    }

    /**
     * Establece la cantidad de días del ingreso actual.
     *
     * <b>pre:</b> debe existir un ingreso actual; dias ≥ 0 <br>
     * <b>post:</b> se actualiza la cantidad de días del ingreso activo
     *
     * @param dias cantidad de días del ingreso
     */
    public void setDias(int dias){
        this.getRegistroIngresoActual().setDias(dias);
    }

    /**
     * Asigna la habitación para el ingreso actual.
     *
     * <b>pre:</b> debe existir un ingreso actual; habitacion puede ser null <br>
     * <b>post:</b> se establece la habitación en el ingreso activo
     *
     * @param habitacion habitación a asignar (o null si no aplica)
     */
    public void setHabitacion(Habitacion habitacion){
        this.getRegistroIngresoActual().setHabitacion(habitacion);
    }

    /**
     * Registra un médico como parte de la atención del ingreso actual.
     *
     * <b>pre:</b> debe existir un ingreso actual;  medico debe ser distinto de null <br>
     * <b>post:</b> se agrega el médico al listado de atención del ingreso activo
     *
     * @param medico profesional que atendió al paciente
     */
    public void addAtendidoPor(IMedico medico){
        this.getRegistroIngresoActual().addAtendidoPor(medico);
    }

    /**
     * Retorna los médicos que atendieron en el ingreso actual
     * <b>pre:</b> debe existir un ingreso actual <br>
     * <b>post:</b> devuelve la lista de profesionales del ingreso activo
     *
     * @return lista de IMedico del ingreso actual
     */
    public ArrayList<IMedico> getAtendidoPor(){
        return this.getRegistroIngresoActual().getAtendidoPor();
    }

    /**
     * Retorna la factura del ingreso actual (si ya fue generada).
     *
     * <b>pre:</b> debe existir un ingreso actual; puede no haberse finalizado aún <br>
     * <b>post:</b> devuelve la factura o null si todavía no se generó
     *
     * @return Factura del ingreso actual o null si todavía no se generó
     */
    public Factura getFactura(){
        return this.getRegistroIngresoActual().getFactura();
    }

    /**
     * Finaliza el ingreso actual y genera su factura.
     *
     * <b>pre:</b> debe existir un ingreso actual con datos coherentes (días, habitación si aplica, médicos) <br>
     * <b>post:</b> se genera y asocia la factura al ingreso activo
     */
    public void finalizarIngreso(){
        this.getRegistroIngresoActual().finalizarIngreso( this );
    }
}
