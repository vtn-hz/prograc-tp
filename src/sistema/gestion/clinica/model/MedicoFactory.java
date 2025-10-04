package sistema.gestion.clinica.model;

import sistema.gestion.clinica.model.decorator.decoratormedico.*;
import sistema.gestion.clinica.model.medico.*;

public class MedicoFactory {
	
	public static final String MEDICO_CLINICO = "clinico";
	public static final String MEDICO_CIRUJANO = "cirujano";
	public static final String MEDICO_PEDIATRA = "pediatra";
	
	public static final String CONTRATACION_PERMANENTE = "contratacion_permanente";
	public static final String CONTRATACION_RESIDENTE  = "contratacion_residente";
	
	public static final String POSTGRADO_MAGISTER = "postgrado_magister";
	public static final String POSTGRADO_DOCTORADO = "postgrado_doctorado";
	
	/**
	 * <b>pre:</b> ∀ param_i tipo String param_i != null / la especialidad existe <br>
	 * <b>post:</b> devuelve un medico sin decoracion de ningun tipo 
	 * @param numeroMatricula
	 * @param dni
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 * @param ciudad
	 * @param especialidad
	 * @return
	 */
	public IMedico create ( String numeroMatricula, 
							String dni, String nombre, 
							String telefono, String direccion, String ciudad, 
							String especialidad 
	) {
		IMedico medico = null;
				
		switch( especialidad ) {
			case MEDICO_CLINICO: 	medico = new MedicoClinico(); break;
			case MEDICO_CIRUJANO:	medico = new MedicoCirujano();break;
			case MEDICO_PEDIATRA: 	medico = new MedicoPediatra();break;
		}
		
		return medico;
	}
	
	/**
	 * <b>pre:</b> ∀ param_i tipo String param_i != null / la especialidad existe / el tipo contrato existe <br>
	 * <b>post:</b> devuelve un medico decorado con DecoratorContrato
	 * @param numeroMatricula
	 * @param dni
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 * @param ciudad
	 * @param especialidad
	 * @param tipoContratacion
	 * @return
	 */
	public IMedico create(
			String numeroMatricula, String dni, String nombre, 
			String telefono, String direccion, String ciudad, 
			String especialidad, String tipoContratacion
	) {
		IMedico medico = this.create(numeroMatricula, dni, nombre, telefono, direccion, ciudad, especialidad);		
		return this.decorateContratacion(medico, tipoContratacion);
	}
	
	
	/**
	 * <b>pre:</b> ∀ param_i tipo String param_i != null / la especialidad existe / el postgrado /  el tipo contrato existe <br>
	 * <b>post:</b> devuelve un medico decorado con DecoratorContrato
	 * @param numeroMatricula
	 * @param dni
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 * @param ciudad
	 * @param especialidad
	 * @param postgrado
	 * @param tipoContratacion
	 * @return
	 */
	public IMedico create(
			String numeroMatricula, String dni, String nombre, 
			String telefono, String direccion, String ciudad, 
			String especialidad, String postgrado, String tipoContratacion
	) {
		IMedico medico = this.create(numeroMatricula, dni, nombre, telefono, direccion, ciudad, especialidad);		
		medico = this.decoratePostgrado(medico, postgrado);
		return this.decorateContratacion(medico, tipoContratacion);
	}
	
	private IMedico decorateContratacion( IMedico medico,  String tipoContratacion)
	{
		
		switch( tipoContratacion ) 
		{
			case CONTRATACION_PERMANENTE: 	medico = new DecoratorMedicoContratacionPermanente( medico ); break;
			case CONTRATACION_RESIDENTE:	medico = new DecoratorMedicoContratacionResidente( medico ); break;
		}
	
		return medico;
	}
	
	private IMedico decoratePostgrado( IMedico medico,  String postgrado)
	{
		
		switch( postgrado ) 
		{
			case POSTGRADO_MAGISTER: 	medico = new DecoratorMedicoPostgradoMagister( medico ); break;
			case POSTGRADO_DOCTORADO:	medico = new DecoratorMedicoPostgradoDoctorado( medico ); break;
		}
	
		return medico;
	}

}
