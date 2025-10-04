package sistema.gestion.clinica.model;


public abstract class Medico implements IMedico 
{
	private static final double HONORARIO_BASICO = 20000;
	
	private String numeroMatricula;

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
