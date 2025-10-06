package mdp.ingenieria.clinicagestion.model.decorator;

import mdp.ingenieria.clinicagestion.model.persona.IMedico;

/**
 * Clase abstracta que representa el decorador base para los objetos de tipo IMedico.
 *
 * <b>invariante:</b> {@code medico} siempre debe ser distinto de null
 */
public abstract class DecoratorMedico implements IMedico
{
	
	protected IMedico medico;

    /**
     * Constructor del decorador base.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> inicializa el decorador con la referencia al médico que será decorado
     *
     * @param medico        instancia de IMedico a decorar
     */
	public DecoratorMedico( IMedico medico ) 
	{
		this.medico = medico;
	}

    /**
     * Retorna el número de matrícula del médico decorado.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> devuelve el número de matrícula del medico.
     *
     * @return número de matrícula del médico
     */
	@Override
	public int getNumeroMatricula() 
	{
		return this.medico.getNumeroMatricula();
	}

    /**
     * Retorna la especialidad del médico decorado.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> devuelve la espcialidad del medico.
     *
     * @return especialidad del médico
     */
	@Override
	public String getEspecialidad()
	{
		return this.medico.getEspecialidad();
	}

    /**
     * Retorna el nombre y apellido del médico decorado.
     *
     * <b>pre:</b> medico no debe ser nulo <br>
     * <b>post:</b> devuelve el nombre y apellido del medico.
     *
     * @return nombre y apellido del médico
     */
	@Override
	public String getNyA()
	{
		return this.medico.getNyA();
	}

}
