package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.decorator.decoratormedico.*;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoCirujano;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoClinico;
import mdp.ingenieria.clinicagestion.model.persona.medico.MedicoPediatra;
/**
 * Fábrica de objetos IMedico que además permite aplicar decoradores de contrato y posgrado.
 */
public class MedicoFactory {
	
	public static final String MEDICO_CLINICO = "clinico";
	public static final String MEDICO_CIRUJANO = "cirujano";
	public static final String MEDICO_PEDIATRA = "pediatra";
	
	public static final String CONTRATACION_PERMANENTE = "contratacion_permanente";
	public static final String CONTRATACION_RESIDENTE  = "contratacion_residente";
	
	public static final String POSTGRADO_MAGISTER = "postgrado_magister";
	public static final String POSTGRADO_DOCTORADO = "postgrado_doctorado";

    /**
     * Crea un médico de la especialidad indicada, sin decoraciones.
     *
     * <b>pre:</b> todos los parámetros String no deben ser nulos ni vacíos; la especialidad debe existir <br>
     * <b>post:</b> retorna una instancia de {@link IMedico} sin decoradores
     *
     * @param numeroMatricula       número de matrícula profesional
     * @param NyA                   nombre y apellido del médico
     * @param dni                   documento de identidad
     * @param telefono              teléfono de contacto
     * @param ciudad                ciudad del domicilio
     * @param direccion             dirección del domicilio
     * @param especialidad          identificador String de la especialidad
     * @return instancia base de IMedico correspondiente a la especialidad
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
     * Crea un médico y aplica un decorador de contratación.
     *
     * <b>pre:</b> todos los parámetros String no deben ser nulos ni vacíos; el tipo de contratación debe existir <br>
     * <b>post:</b> retorna un  IMedico decorado con la modalidad de contratación
     *
     * @param numeroMatricula       número de matrícula profesional
     * @param NyA                   nombre y apellido del médico
     * @param dni                   documento de identidad
     * @param telefono              teléfono de contacto
     * @param ciudad                ciudad del domicilio
     * @param direccion             dirección del domicilio
     * @param especialidad          identificador textual de la especialidad
     * @param tipoContratacion      identificador textual del tipo de contratación
     * @return instancia de IMedico decorada con contratación
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
     * Crea un médico y aplica decoradores de posgrado y contratación (en ese orden).
     *
     * <b>pre:</b> todos los parámetros String no deben ser nulos ni vacíos; el tipo de contratación y el posgrado deben existir <br>
     * <b>post:</b> retorna un IMedico decorado con posgrado y contratación
     *
     * @param numeroMatricula       número de matrícula profesional
     * @param NyA                   nombre y apellido del médico
     * @param dni                   documento de identidad
     * @param telefono              teléfono de contacto
     * @param ciudad                ciudad del domicilio
     * @param direccion             dirección del domicilio
     * @param especialidad          identificador textual de la especialidad
     * @param tipoContratacion      identificador textual del tipo de contratación
     * @param postgrado             identificador textual del posgrado
     * @return instancia de IMedico decorada con posgrado y contratación
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

    /**
     * Aplica el decorador de contratación según el identificador recibido.
     *
     * <b>pre:</b> medico no debe ser null; el tipo de contratación existe <br>
     * <b>post:</b> retorna el médico decorado.
     *
     * @param medico                instancia base o ya decorada de IMedico
     * @param tipoContratacion      identificador textual del tipo de contratación
     * @return médico decorado con contratación (o el mismo si no coincide ningún tipo)
     */
	private IMedico decorateContratacion( IMedico medico,  String tipoContratacion)
	{
		
		switch( tipoContratacion ) 
		{
			case CONTRATACION_PERMANENTE: 	medico = new DecoratorMedicoContratacionPermanente( medico ); break;
			case CONTRATACION_RESIDENTE:	medico = new DecoratorMedicoContratacionResidente( medico ); break;
		}
	
		return medico;
	}

    /**
     * Aplica el decorador de posgrado según el identificador recibido.
     *
     * <b>pre:</b> medico no debe ser null; el posgrado existe <br>
     * <b>post:</b> retorna el médico decorado
     *
     * @param medico            instancia base o ya decorada de IMedico
     * @param postgrado         identificador textual del posgrado
     * @return médico decorado con posgrado (o el mismo si no coincide ningún tipo)
     */
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
