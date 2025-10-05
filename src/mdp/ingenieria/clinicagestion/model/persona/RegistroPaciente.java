package mdp.ingenieria.clinicagestion.model.persona;
import java.util.ArrayList;

import sistema.gestion.clinica.model.persona.Paciente;

public class RegistroPaciente {
    private Paciente paciente;
    private ArrayList<RegistroIngreso> registroIngresos;
    private int ingresoActual;

    public RegistroPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.registroIngresos = new ArrayList<RegistroIngreso>();
        this.ingresoActual = -1;
    }

    public void addRegistroIngreso(){
        this.registroIngresos.add(new RegistroIngreso);
        this.ingresoActual+=1;
    }

    public RegistroIngreso getRegistroIngresoActual(){
        return this.registroIngresos.get(this.ingresoActual);
    }

    public ArrayList<RegistroIngreso> getRegistroIngresos(){
        return this.registroIngresos;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }

    public void setDias(int dias){
        this.registroIngresos.get(this.ingresoActual).setDias(dias);
    }

    public void setHabitacion(Habitacion habitacion){
        this.registroIngresos.get(this.ingresoActual).setHabitacion(habitacion);
    }

    public void addAtendidoPor(IMedico medico){
        this.registroIngresos.get(this.ingresoActual).addAtendidoPor(medico);
    }

    public Factura getFactura(){
        return this.registroIngresos.get(this.ingresoActual).getFactura();
    }

    public void finalizarIngreso(){
        this.registroIngresos.get(this.ingresoActual).finalizarIngreso();
    }
}
