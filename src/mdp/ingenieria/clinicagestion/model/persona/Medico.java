package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;

public abstract class Medico extends Persona implements IMedico 
{
	private static final double HONORARIO_BASICO = 20000;
	
	private int numeroMatricula;
	
	public Medico(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(NyA, dni, domicilio);
		this.numeroMatricula = numeroMatricula;
	}

	/**
	 *  @return String
	 */
	public int getNumeroMatricula() 
	{
		return this.numeroMatricula;
	}
	
	/**
	 *  @return double
	 */
	public double getHonorario() 
	{
		return HONORARIO_BASICO;
	}
}	
