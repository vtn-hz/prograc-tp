package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

/**
 * Decorador concreto que representa la contratación permanente de un médico.
 * Extiende DecoratorMedicoContratacion.
 */
public class DecoratorMedicoContratacionPermanente extends DecoratorMedicoContratacion {
	
	private static final double CONTRATACION_AUMENTO = 0.10;

    /**
     * Constructor del decorador de contratación permanente.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> inicializa el decorador con el médico cuya contratación es permanente
     *
     * @param medico    instancia de IMedico que será decorada con la contratación permanente
     */
	public DecoratorMedicoContratacionPermanente( IMedico medico ) 
	{	
		super( medico );
	}

    /**
     * Retorna el honorario del médico ajustado según la contratación permanente.
     * Aplica un incremento del 10% sobre el honorario base.
     *
     * <b>pre:</b> el médico decorado debe estar correctamente inicializado <br>
     * <b>post:</b> devuelve el honorario total incrementado en un 10%
     *
     * @return honorario total del médico con contratación permanente
     */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + CONTRATACION_AUMENTO);
	}
}
