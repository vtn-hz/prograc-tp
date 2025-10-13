package mdp.ingenieria.clinicagestion.exception;
/**
 * Excepción general que representa errores relacionados con un Paciente.
 */
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class PacienteException extends Exception
{
    private Paciente paciente;

    /**
     * Crea una nueva excepción asociada a un paciente.
     *
     * <b>pre:</b> el mensaje no debe ser nulo ni vacío, y el paciente debe estar correctamente instanciado <br>
     * <b>post:</b> se crea una excepción con el mensaje y el paciente vinculado al error
     *
     * @param msg mensaje descriptivo del error ocurrido
     * @param paciente instancia del paciente relacionada con la excepción
     */
    public PacienteException(String msg, Paciente paciente)
    {
    	super(msg);
    	this.paciente = paciente;
    }

    /**
     * Retorna el paciente asociado a la excepción.
     *
     * <b>pre:</b> la excepción fue correctamente instanciada con un paciente válido <br>
     * <b>post:</b> devuelve la referencia al paciente vinculado al error
     *
     * @return objeto Paciente asociado a la excepción
     */
    public Paciente getPaciente(){
        return this.paciente;
    }
	
}
