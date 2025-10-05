package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

public class DecoratorMedicoContratacionPermanente extends DecoratorMedicoContratacion {
	
	private static final double CONTRATACION_AUMENTO = 0.10;
	
	public DecoratorMedicoContratacionPermanente( IMedico medico ) 
	{	
		super( medico );
	}
	
	/**
	 * <b>post:</b> devuelve el honorario decorado por contratacion permanente 
	 * @return double
	 */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + CONTRATACION_AUMENTO);
	}
}
