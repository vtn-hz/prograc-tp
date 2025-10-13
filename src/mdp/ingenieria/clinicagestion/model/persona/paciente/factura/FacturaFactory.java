package mdp.ingenieria.clinicagestion.model.persona.paciente.factura;

import java.time.LocalDateTime;

import mdp.ingenieria.clinicagestion.model.clinica.Habitacion;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;
import mdp.ingenieria.clinicagestion.model.persona.paciente.registro.RegistroIngreso;
import mdp.ingenieria.clinicagestion.model.persona.paciente.registro.RegistroPaciente;
/**
 * Fábrica de objetos Factura a partir de la información de un RegistroPaciente y su RegistroIngreso actual.
 * <p>
 * Construye una factura con: datos del paciente, fechas de ingreso/egreso, detalle de habitación (si aplica) e ítems por
 * cada IMedico que atendió al paciente.
 * </p>
 */
public class FacturaFactory
{
    /**
     * Crea una Factura basada en el RegistroPaciente provisto.
     *
     * <b>pre:</b>  registroPaciente no debe ser nulo; debe existir un RegistroIngreso actual no nulo; los datos (fechas, días y, si aplica,
     * habitación) deben estar correctamente inicializados <br>
     * <b>post:</b> retorna una Factura consistente con la información del registro (los ítems se crean a partir de los médicos que atendieron)
     *
     * @param registroPaciente registro del paciente desde el cual se genera la factura
     * @return instancia de {@link Factura} con los datos cargados
     * @throws NullPointerException si registroPaciente o su getRegistroIngresoActual() es nulo
     */
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
