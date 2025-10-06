package mdp.ingenieria.clinicagestion.model.decorator.decoratormedico;

import mdp.ingenieria.clinicagestion.model.decorator.DecoratorMedico;
import mdp.ingenieria.clinicagestion.model.persona.IMedico;

/**
 * Clase abstracta que actúa como decorador base para los distintos tipos de contratacion de médicos.
 * Extiende DecoratorMedico.
 */
public abstract class DecoratorMedicoContratacion extends DecoratorMedico 
{
    /**
     * Constructor del decorador de contratación médica.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> inicializa el decorador con la referencia al médico que será extendido
     *
     * @param medico    instancia de IMedico que se desea decorar
     */
	public DecoratorMedicoContratacion( IMedico medico ) 
	{	
		super( medico );
	}
	
}
