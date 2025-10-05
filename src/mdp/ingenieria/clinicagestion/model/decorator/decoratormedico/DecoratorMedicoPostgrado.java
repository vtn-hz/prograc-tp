package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.decorator.DecoratorMedico;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;

public abstract class DecoratorMedicoPostgrado extends DecoratorMedico 
{

	public DecoratorMedicoPostgrado( IMedico medico ) 
	{	
		super( medico );
	}
}
