package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;

public class AmbulanciaInteractorFactory {

	public static final String ASOCIADO = "ASOCIADO";

	public static final String OPERARIO = "OPERARIO";

    /**
     * Crea una instancia de Persona del tipo solicitado, asociada a una ambulancia.
     *
     * <b>pre:</b> personaType, NyA, dni, telefono, ciudad, direccion y ambulancia
     * no deben ser nulos ni vacíos; personaType debe ser ASOCIADO u OPERARIO <br>
     * <b>post:</b> se devuelve una instancia de Asociado u Operario según el tipo
     *
     * @param personaType tipo de persona a crear
     * @param NyA nombre y apellido
     * @param dni documento identificatorio
     * @param telefono teléfono de contacto
     * @param ciudad ciudad del domicilio
     * @param direccion dirección específica del domicilio
     * @param ambulancia ambulancia asociada a la persona
     * @return instancia creada según el tipo indicado
     */
	public static Persona create(
		String personaType, String NyA, String dni,
		String telefono, String ciudad, String direccion,
		Ambulancia ambulancia
	) {
		assert personaType != null;
		assert NyA != null && !NyA.isEmpty();
		assert dni != null && !dni.isEmpty();
		assert telefono != null && !telefono.isEmpty();
		assert ciudad != null && !ciudad.isEmpty();
		assert direccion != null && !direccion.isEmpty();
		assert ambulancia != null;

		Persona persona = null;
		Domicilio domicilio = new Domicilio(telefono, ciudad, direccion);

		switch(personaType)
		{
			case ASOCIADO:
				persona = new Asociado(NyA, dni, domicilio, ambulancia);
				break;

			case OPERARIO:
				persona = new Operario(NyA, dni, domicilio, ambulancia);
				break;
		}

		return persona;
	}

    /**
     * Crea una persona a partir de un DTO, asociándola a una ambulancia.
     *
     * <b>pre:</b> dto y ambulancia no deben ser nulos; personaType debe ser válido <br>
     * <b>post:</b> se crea una instancia equivalente a partir del DTO
     *
     * @param personaType tipo de persona a crear
     * @param dto datos de la persona
     * @param ambulancia ambulancia asociada a la persona
     * @return instancia creada según los datos del DTO
     */
	public static Persona create(
		String personaType,
		PersonaDTO dto,
		Ambulancia ambulancia
	) {
		return create(
			personaType,
			dto.getNyA(),
			dto.getDni(),
			dto.getTelefono(),
			dto.getCiudad(),
			dto.getDireccion(),
			ambulancia
		);
	}
}
