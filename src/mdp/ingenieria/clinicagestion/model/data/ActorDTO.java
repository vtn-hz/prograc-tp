package mdp.ingenieria.clinicagestion.model.data;

public class ActorDTO {

	private String NyA;
	
	private String dni;
	
	private String telefono;
	
	private String ciudad;
	
	private String direccion;
	
	private int interactionCount;

	public ActorDTO() {}

	public ActorDTO(String NyA, String dni, String telefono, String ciudad, String direccion, int interactionCount) {
		this.NyA = NyA;
		this.dni = dni;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.interactionCount = interactionCount;
	}

	public String getNyA() {
		return NyA;
	}

	public String getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public int getInteractionCount() 
	{
		return this.interactionCount;
	}

	public void setNyA(String NyA) {
		this.NyA = NyA;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setInteractionCount(int interactionCount) 
	{
		this.interactionCount = interactionCount;
	}
}
