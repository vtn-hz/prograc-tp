package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

/**
 * Decorador concreto que representa un médico con postgrado de doctorado.
 * Extiende DecoratorMedicoPostgrado.
 */
public class DecoratorMedicoPostgradoDoctorado extends DecoratorMedicoPostgrado {

	private static final double POSTGRADO_AUMENTO = 0.10;

    /**
     * Constructor del decorador de postgrado tipo doctorado.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> inicializa el decorador con el médico al cual se decorara
     *
     * @param medico instancia de IMedico que será decorada con el postgrado de doctorado
     */
	public DecoratorMedicoPostgradoDoctorado(IMedico medico)
	{
		super(medico);
	}

    /**
     * Retorna el honorario del médico ajustado según su postgrado de doctorado.
     * Aplica un incremento del 10% sobre el honorario base.
     *
     * <b>pre:</b> el médico decorado debe estar correctamente inicializado <br>
     * <b>post:</b> devuelve el honorario total incrementado en un 10%
     *
     * @return honorario total del médico con postgrado de doctorado
     */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + POSTGRADO_AUMENTO);
	}

}
