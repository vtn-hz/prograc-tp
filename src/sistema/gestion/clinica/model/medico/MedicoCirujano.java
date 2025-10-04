package sistema.gestion.clinica.model.medico;

import sistema.gestion.clinica.model.Medico;

public class MedicoCirujano extends Medico 
{
	
	private static final double ESPECIALIDAD_AUMENTO = 0.10;
	
	public MedicoCirujano() 
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
