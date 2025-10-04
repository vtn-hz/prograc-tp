package sistema.gestion.clinica.model.decorator.decoratormedico;

import sistema.gestion.clinica.model.IMedico;
import sistema.gestion.clinica.model.decorator.DecoratorMedico;

public abstract class DecoratorMedicoPostgrado extends DecoratorMedico 
{

	public DecoratorMedicoPostgrado( IMedico medico ) 
	{	
		super( medico );
	}
}
