package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.decorator.DecoratorMedico;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;


public abstract class DecoratorMedicoContratacion extends DecoratorMedico 
{

	public DecoratorMedicoContratacion( IMedico medico ) 
	{	
		super( medico );
	}
	
}
