package mdp.ingenieria.clinicagestion.model.persona.paciente.registro;

import java.util.Stack;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;

public class RegistroPaciente {
	
    private Paciente paciente;
    
    private Stack<RegistroIngreso> registroIngresos;    
    
    public RegistroPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.registroIngresos = new Stack<>();
    }

    public void addRegistroIngreso(){
        this.registroIngresos.push( new RegistroIngreso() );
    }

    public RegistroIngreso getRegistroIngresoActual(){
        return this.registroIngresos.peek();
    }

    public  Stack<RegistroIngreso>  getRegistroIngresos(){
        return this.registroIngresos;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }

    public void setDias(int dias){
        this.getRegistroIngresoActual().setDias(dias);
    }

    public void setHabitacion(Habitacion habitacion){
        this.getRegistroIngresoActual().setHabitacion(habitacion);
    }

    public void addAtendidoPor(IMedico medico){
        this.getRegistroIngresoActual().addAtendidoPor(medico);
    }

    public Factura getFactura(){
        return this.getRegistroIngresoActual().getFactura();
    }

    public void finalizarIngreso(){
        this.getRegistroIngresoActual().finalizarIngreso( this );
    }
}
