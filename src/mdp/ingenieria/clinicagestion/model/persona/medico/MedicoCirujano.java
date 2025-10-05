package mdp.ingenieria.clinicagestion.model.persona.medico;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.Medico;

public class MedicoCirujano extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.10;
	
	public MedicoCirujano(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(numeroMatricula, NyA, dni, domicilio);
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
