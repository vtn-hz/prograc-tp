package mdp.ingenieria.clinicagestion.model.persona.medico;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.Medico;

public class MedicoPediatra extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.07;
	
	public MedicoPediatra(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
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
