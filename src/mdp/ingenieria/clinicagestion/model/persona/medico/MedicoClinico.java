package mdp.ingenieria.clinicagestion.model.persona.medico;

import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.Medico;

/**
 * Representa a un médico clinico.
 * Extiende Medico
 */
public class MedicoClinico extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.05;

    /**
     * Contrucor de MedicoClinico con los datos especificados.
     *
     * <b>pre:</b> los parámetros no deben ser nulos ni vacíos, y la matrícula debe ser válida <br>
     * <b>post:</b> se crea un médico cirujano con la información proporcionada
     *
     * @param numeroMatricula   número de matrícula del médico
     * @param NyA               nombre y apellido del médico
     * @param dni               documento nacional de identidad
     * @param domicilio         información de contacto y ubicación del médico
     */
	public MedicoClinico(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(numeroMatricula, NyA, dni, domicilio);
	}

    /**
     * Retorna la especialidad del médico.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el string "clinico"
     *
     * @return especialidad del médico, en este caso "clinico"
     */
	@Override
	public String getEspecialidad() 
	{
		return "clinico";
	}

    /**
     * Calcula y retorna el honorario del médico clinico.
     * Aplica un incremento del 5% sobre el honorario base.
     *
     * <b>pre:</b> el honorario base debe estar correctamente definido en la clase Medico <br>
     * <b>post:</b> devuelve el honorario con el aumento correspondiente a la especialidad
     *
     * @return honorario total del médico clinico
     */
	@Override
	public double getHonorario() 
	{
		return super.getHonorario() * (1 + ESPECIALIDAD_AUMENTO);
	}
}
