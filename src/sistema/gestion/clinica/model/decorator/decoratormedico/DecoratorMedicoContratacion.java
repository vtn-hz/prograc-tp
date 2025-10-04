package sistema.gestion.clinica.model.decorator.decoratormedico;

import sistema.gestion.clinica.model.decorator.DecoratorMedico;
import sistema.gestion.clinica.model.IMedico;


public abstract class DecoratorMedicoContratacion extends DecoratorMedico 
{

	public DecoratorMedicoContratacion( IMedico medico ) 
	{	
		super( medico );
	}
	
}
