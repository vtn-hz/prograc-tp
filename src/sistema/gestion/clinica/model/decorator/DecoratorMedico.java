package sistema.gestion.clinica.model.decorator;

import sistema.gestion.clinica.model.IMedico;


/**
 * <b>invariante: </b> medico siempre != null
 */
public abstract class DecoratorMedico implements IMedico
{
	
	protected IMedico medico;
	
	public DecoratorMedico( IMedico medico ) 
	{
		this.medico = medico;
	}

	@Override
	public String getNumeroMatricula() 
	{
		return this.medico.getNumeroMatricula();
	}

	@Override
	public String getEspecialidad()
	{
		return this.medico.getEspecialidad();
	}

}
