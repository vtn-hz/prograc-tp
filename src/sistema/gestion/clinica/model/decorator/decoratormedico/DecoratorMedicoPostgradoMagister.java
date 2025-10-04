package sistema.gestion.clinica.model.decorator.decoratormedico;

import sistema.gestion.clinica.model.IMedico;

public class DecoratorMedicoPostgradoMagister extends DecoratorMedicoPostgrado {

	private static final double POSTGRADO_AUMENTO = 0.05;
	
	public DecoratorMedicoPostgradoMagister(IMedico medico)
	{
		super(medico);
	}

	/**
	 * <b>post:</b> devuelve el honorario decorado por postgrado magister
	 * @return double
	 */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + POSTGRADO_AUMENTO);
	}

}
