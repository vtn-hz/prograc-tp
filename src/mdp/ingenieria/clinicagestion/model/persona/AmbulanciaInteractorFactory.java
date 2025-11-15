package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Ambulancia;
import mdp.ingenieria.clinicagestion.model.Domicilio;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;

public class AmbulanciaInteractorFactory {

	public static final String ASOCIADO = "ASOCIADO";

	public static final String OPERARIO = "OPERARIO";

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
