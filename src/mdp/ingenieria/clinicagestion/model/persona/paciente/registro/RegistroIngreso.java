package mdp.ingenieria.clinicagestion.model.persona.paciente.registro;

import java.time.LocalDateTime;
import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.Factura;
import mdp.ingenieria.clinicagestion.model.persona.paciente.factura.FacturaFactory;

public class RegistroIngreso {
	
    private LocalDateTime fechaIngreso;
    
    private int dias;
    
    private Habitacion habitacion;
    
    private ArrayList<IMedico> atendidoPor;
    
    private Factura factura;
    
    private FacturaFactory facturaFactory;
    
    public RegistroIngreso() {
        this.fechaIngreso = LocalDateTime.now();
        this.atendidoPor = new ArrayList<>();
        this.dias = 0;
        this.factura = null;
        this.facturaFactory = new FacturaFactory();
    }
    
    public LocalDateTime getFechaIngreso() {
    	return this.fechaIngreso;
    }
    
    public void setDias(int dias) {
        this.dias = dias;
    }
    
    public int getDias() {
    	return this.dias;
    }
    
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    public Habitacion getHabitacion() {
    	return this.habitacion;
    }
        
    public void addAtendidoPor(IMedico medico) {
        atendidoPor.add(medico);
    }
    
    public ArrayList<IMedico> getAtendidoPor() {
        return atendidoPor;
    }
    
    public void finalizarIngreso( RegistroPaciente registroPaciente ) {
    	this.factura = this.facturaFactory.create( registroPaciente );
    }
    
    public Factura getFactura() {
        return this.factura;
    }
}