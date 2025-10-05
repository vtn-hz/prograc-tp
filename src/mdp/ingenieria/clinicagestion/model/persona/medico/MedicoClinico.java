package mdp.ingenieria.clinicagestion.model.persona.medico;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.Medico;

public class MedicoClinico extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.05;
	
	public MedicoClinico(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
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
