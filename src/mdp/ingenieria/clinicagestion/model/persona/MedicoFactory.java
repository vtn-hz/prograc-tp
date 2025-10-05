package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.decorator.decoratormedico.*;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoCirujano;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoClinico;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoPediatra;

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
	public IMedico create ( int numeroMatricula, 
							String NyA, String dni, 
							String telefono, String ciudad, String direccion, 
							String especialidad 
	) {
		IMedico medico = null;
		Domicilio domicilio = new Domicilio(telefono, ciudad, direccion); 
				
		switch( especialidad ) {
			case MEDICO_CLINICO: 	medico = new MedicoClinico(numeroMatricula, NyA, dni, domicilio); break;
			case MEDICO_CIRUJANO:	medico = new MedicoCirujano(numeroMatricula, NyA, dni, domicilio);break;
			case MEDICO_PEDIATRA: 	medico = new MedicoPediatra(numeroMatricula, NyA, dni, domicilio);break;
		}
		
		return medico;
	}
	
	/**
	 * <b>pre:</b> ∀ param_i tipo String param_i != null / la especialidad existe / el tipo contrato existe <br>
	 * <b>post:</b> devuelve un medico decorado con DecoratorContrato
	 * @param numeroMatricula
	 * @param NyA
	 * @param dni
	 * @param telefono
	 * @param ciudad
	 * @param direccion
	 * @param especialidad
	 * @param tipoContratacion
	 * @return
	 */
	public IMedico create(
			int numeroMatricula, 
			String NyA, String dni, 
			String telefono, String ciudad, String direccion, 
			String especialidad, String tipoContratacion
	) {
		IMedico medico = this.create(numeroMatricula, NyA, dni, telefono, ciudad, direccion, especialidad);		
		return this.decorateContratacion(medico, tipoContratacion);
	}
	
		
	/**
	 * <b>pre:</b> ∀ param_i tipo String param_i != null / la especialidad existe / el postgrado /  el tipo contrato existe <br>
	 * <b>post:</b> devuelve un medico decorado con DecoratorContrato
	 * @param numeroMatricula
	 * @param NyA
	 * @param dni
	 * @param telefono
	 * @param ciudad
	 * @param direccion
	 * @param especialidad
	 * @param tipoContratacion
	 * @param postgrado
	 * @return
	 */
	public IMedico create(
			int numeroMatricula, 
			String NyA, String dni,  
			String telefono, String ciudad, String direccion, 
			String especialidad, String tipoContratacion, String postgrado
	) {
		IMedico medico = this.create(numeroMatricula, NyA, dni, telefono, ciudad, direccion, especialidad);		
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
