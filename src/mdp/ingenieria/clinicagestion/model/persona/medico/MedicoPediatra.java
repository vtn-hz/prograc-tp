package mdp.ingenieria.clinicagestion.model.persona.medico;

import mdp.ingenieria.clinicagestion.model.clinica.Domicilio;
import mdp.ingenieria.clinicagestion.model.persona.Medico;

/**
 * Representa a un médico pediatra.
 * Extiende Medico
 */
public class MedicoPediatra extends Medico 
{
	private static final double ESPECIALIDAD_AUMENTO = 0.07;

    /**
     * Contrucor de MedicoPediatra con los datos especificados.
     *
     * <b>pre:</b> los parámetros no deben ser nulos ni vacíos, y la matrícula debe ser válida <br>
     * <b>post:</b> se crea un médico cirujano con la información proporcionada
     *
     * @param numeroMatricula   número de matrícula del médico
     * @param NyA               nombre y apellido del médico
     * @param dni               documento nacional de identidad
     * @param domicilio         información de contacto y ubicación del médico
     */
	public MedicoPediatra(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(numeroMatricula, NyA, dni, domicilio);
	}

    /**
     * Retorna la especialidad del médico.
     *
     * <b>pre:</b> la instancia debe estar correctamente inicializada <br>
     * <b>post:</b> devuelve el string "Pediatra"
     *
     * @return especialidad del médico, en este caso "Pediatra"
     */
	@Override
	public String getEspecialidad() 
	{
		return "pediatra";
	}

    /**
     * Calcula y retorna el honorario del médico pediatra.
     * Aplica un incremento del 7% sobre el honorario base.
     *
     * <b>pre:</b> el honorario base debe estar correctamente definido en la clase Medico <br>
     * <b>post:</b> devuelve el honorario con el aumento correspondiente a la especialidad
     *
     * @return honorario total del médico pediatra
     */
	@Override
	public double getHonorario() 
	{
		return super.getHonorario() * (1 + ESPECIALIDAD_AUMENTO);
	}
}
