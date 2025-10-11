package mdp.ingenieria.clinicagestion.model.persona;

import mdp.ingenieria.clinicagestion.model.Domicilio;

/**
 * Clase base abstracta para profesionales médicos de la clinica
 */
public abstract class Medico extends Persona implements IMedico 
{
	private static final double HONORARIO_BASICO = 20000;
	
	private int numeroMatricula;

    /**
     * Cronstructor de un médico con los datos provistos.
     *
     * <b>pre:</b> NyA, dni y domicilio no deben ser nulos ni vacíos; numeroMatricula debe ser válido (no negativo) <br>
     * <b>post:</b> se inicializa la instancia con identidad personal y profesional
     *
     * @param numeroMatricula       número de matrícula profesional
     * @param NyA                   nombre y apellido
     * @param dni                   documento de identidad
     * @param domicilio             domicilio del médico
     */
    public Medico(int numeroMatricula, String NyA, String dni, Domicilio domicilio) {
		super(NyA, dni, domicilio);
		this.numeroMatricula = numeroMatricula;
	}

    /**
     * Retorna el número de matrícula profesional.
     * <b>post:</b> devuelve el identificador profesional único
     *
     * @return número de matrícula
     */
	public int getNumeroMatricula() 
	{
		return this.numeroMatricula;
	}

    /**
     * Retorna el honorario base del médico.
     * <b>post:</b> devuelve el honorario base definido por el sistema
     *
     * @return honorario base
     */
	public double getHonorario() 
	{
		return HONORARIO_BASICO;
	}
}	
