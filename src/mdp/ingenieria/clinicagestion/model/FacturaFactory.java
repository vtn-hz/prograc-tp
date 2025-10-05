package mdp.ingenieria.clinicagestion.model;

import java.time.LocalDateTime;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.RegistroIngreso;
import mdp.ingenieria.clinicagestion.model.persona.RegistroPaciente;

public class FacturaFactory
{
	
	public Factura create( RegistroPaciente registroPaciente ) {
		Factura factura = new Factura();
		
		RegistroIngreso registroIngreso = registroPaciente.getRegistroIngresoActual();
		
		int cantidadDias = registroIngreso.getDias();
		LocalDateTime fechaIngreso = registroIngreso.getFechaIngreso();
		Habitacion habitacion = registroIngreso.getHabitacion();
		
		
		factura.setNombrePaciente( registroPaciente.getPaciente().getNyA() );		
		factura.setFechaIngreso( fechaIngreso );
		factura.setFechaEgreso( fechaIngreso.plusDays( cantidadDias ) ); 
		
		factura.setUtilizoHabitacion( habitacion != null );
		if (factura.getUtilizoHabitacion()) {
			factura.setTipoHabitacion( habitacion.getTipoHabitacion() );
			factura.setCantidadDias( cantidadDias );
			factura.setCostoHabitacion( habitacion.getCostoTotal( cantidadDias ) );
		}
		
		for (IMedico medico : registroIngreso.getAtendidoPor()) {
			factura.addItem(
				new FacturaItem( 
						medico.getNumeroMatricula(),
						medico.getNyA(),
						medico.getEspecialidad(),
						medico.getHonorario()
				)
			);
		}
		
		return factura;
	}

}
