package sistema.gestion.clinica.model.decorator.decoratormedico;

import sistema.gestion.clinica.model.IMedico;

public class DecoratorMedicoPostgradoDoctorado extends DecoratorMedicoPostgrado {

	private static final double POSTGRADO_AUMENTO = 0.10;
	
	public DecoratorMedicoPostgradoDoctorado(IMedico medico)
	{
		super(medico);
	}

	/**
	 * <b>post:</b> devuelve el honorario decorado por postgrado doctorado
	 * @return double
	 */
	@Override
	public double getHonorario() 
	{
		return this.medico.getHonorario() * (1 + POSTGRADO_AUMENTO);
	}

}
