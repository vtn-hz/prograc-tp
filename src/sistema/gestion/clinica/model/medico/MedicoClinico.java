package sistema.gestion.clinica.model.medico;

import sistema.gestion.clinica.model.Medico;
import sistema.gestion.clinica.modelo.Domicilio;

public class MedicoClinico extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.05;
	
	public MedicoClinico(String numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(numeroMatricula, NyA, dni, domicilio);
	}

	@Override
	public String getEspecialidad() 
	{
		return "clinico";
	}
	
	@Override
	public double getHonorario() 
	{
		return super.getHonorario() * (1 + ESPECIALIDAD_AUMENTO);
	}
}
