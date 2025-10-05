package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

public class DecoratorMedicoContratacionResidente extends DecoratorMedicoContratacion {
	
	private static final double CONTRATACION_AUMENTO = 0.05;
	
	public DecoratorMedicoContratacionResidente( IMedico medico ) 
	{	
		super( medico );
	}

	/**
	 * <b>post:</b> devuelve el honorario decorado por contratacion residente 
	 * @return double
	 */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + CONTRATACION_AUMENTO);
	}
}
