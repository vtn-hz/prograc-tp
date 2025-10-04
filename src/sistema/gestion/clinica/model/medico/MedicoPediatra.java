package sistema.gestion.clinica.model.medico;

import sistema.gestion.clinica.model.Medico;

public class MedicoPediatra extends Medico 
{
	
	private static final double ESPECIALIDAD_AUMENTO = 0.07;
	
	public MedicoPediatra() 
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
