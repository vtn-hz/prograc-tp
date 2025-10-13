package mdp.ingenieria.clinicagestion.model.persona;

/**
 * Comportamiento predeterminado para profesionales médicos de la clinica.
 */
public interface IMedico 
{
    /**
     * Retorna el nombre y apellido del médico.
     *
     * <b>pre:</b> la implementación debe garantizar un valor no nulo ni vacío <br>
     * <b>post:</b> se devuelve el identificador nominal del profesional
     *
     * @return nombre y apellido del médico
     */
	String getNyA();

    /**
     * Retorna el número de matrícula profesional.
     *
     * <b>pre:</b> la implementación debe garantizar un número válido (no negativo) <br>
     * <b>post:</b> se devuelve el identificador profesional único
     *
     * @return número de matrícula del médico
     */
	int getNumeroMatricula();

    /**
     * Retorna la especialidad del médico.
     *
     * <b>pre:</b> la implementación debe garantizar un valor no nulo ni vacío <br>
     * <b>post:</b> se devuelve la especialidad declarada
     *
     * @return especialidad del médico
     */
	String getEspecialidad();

    /**
     * Retorna el honorario base del médico.
     *
     * <b>pre:</b> la implementación debe garantizar un valor coherente (≥ 0) <br>
     * <b>post:</b> se devuelve el monto base a utilizar en facturación
     *
     * @return honorario base del médico
     */
	double getHonorario();
}
