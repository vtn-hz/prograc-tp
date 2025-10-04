package sistema.gestion.clinica.model;

import sistema.gestion.clinica.modelo.Domicilio;
import sistema.gestion.clinica.modelo.Persona;

public abstract class Medico extends Persona implements IMedico 
{
	private static final double HONORARIO_BASICO = 20000;
	
	private String numeroMatricula;
	
	public Medico(String numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(NyA, dni, domicilio);
		this.numeroMatricula = numeroMatricula;
	}

	/**
	 *  @return String
	 */
	public String getNumeroMatricula() 
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
