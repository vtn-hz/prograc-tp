package mdp.ingenieria.clinicagestion.model.decorator;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;


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
	public int getNumeroMatricula() 
	{
		return this.medico.getNumeroMatricula();
	}

	@Override
	public String getEspecialidad()
	{
		return this.medico.getEspecialidad();
	}

}
