package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

/**
 * Decorador concreto que representa la contratación de residente de un médico.
 * Extiende DecoratorMedicoContratacion.
 */
public class DecoratorMedicoContratacionResidente extends DecoratorMedicoContratacion {
	
	private static final double CONTRATACION_AUMENTO = 0.05;

    /**
     * Constructor del decorador de contratación residente.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> inicializa el decorador con el médico al cual se aplicará el incremento de honorario
     *
     * @param medico instancia de IMedico que será decorada con la ontratación residente
     */
	public DecoratorMedicoContratacionResidente( IMedico medico ) 
	{	
		super( medico );
	}

    /**
     * Retorna el honorario del médico ajustado según la contratación residente.
     * Aplica un incremento del 5% sobre el honorario base.
     *
     * <b>pre:</b> el médico decorado debe estar correctamente inicializado <br>
     * <b>post:</b> devuelve el honorario total incrementado en un 5%
     *
     * @return honorario total del médico con contratación residente
     */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + CONTRATACION_AUMENTO);
	}
}
