package sistema.gestion.clinica.model.medico;

import sistema.gestion.clinica.model.Medico;
import sistema.gestion.clinica.modelo.Domicilio;

public class MedicoPediatra extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.07;
	
	public MedicoPediatra(String numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(numeroMatricula, NyA, dni, domicilio);
	}

	@Override
	public String getEspecialidad() 
	{
		return "pediatra";
	}
	
	@Override
	public double getHonorario() 
	{
		return super.getHonorario() * (1 + ESPECIALIDAD_AUMENTO);
	}
}
