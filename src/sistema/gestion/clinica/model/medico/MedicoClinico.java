package sistema.gestion.clinica.model.medico;

import sistema.gestion.clinica.model.Medico;

public class MedicoClinico extends Medico 
{
	
	private static final double ESPECIALIDAD_AUMENTO = 0.05;
	
	public MedicoClinico() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getEspecialidad() 
	{
		return "cirujano";
	}
	
	@Override
	public double getHonorario() 
	{
		return super.getHonorario() * (1 + ESPECIALIDAD_AUMENTO);
	}
}
