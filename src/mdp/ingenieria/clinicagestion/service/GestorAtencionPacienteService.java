package mdp.ingenieria.clinicagestion.service;
import java.util.LinkedList;

import java.util.ArrayList;

import mdp.ingenieria.clinicagestion.exception.PacienteNoAtendidoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoEncontradoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoIngresadoException;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;
/**
 * Servicio que gestiona el flujo de pacientes entre la sala de espera y la atención.
 */
public class GestorAtencionPacienteService {
	
    private LinkedList<Paciente> pacientesEspera;
    
    private ArrayList<Paciente> pacientesAtencion;

    /**
     * Constructor del gestor con estructuras vacías y contador de orden en cero.
     * <b>post:</b> lista de atención y mapa de espera inicializados; ultimoNroOrden = 0
     */
    public GestorAtencionPacienteService() {
        this.pacientesEspera = new LinkedList<Paciente>();
        this.pacientesAtencion = new ArrayList<Paciente>();
    }

    /**
     * Anuncia un paciente para sala de espera: se le asigna número de orden y se lo ubica.
     *
     * <b>pre:</b> paciente no debe ser null <br>
     * <b>post:</b> el paciente queda en pacientesEspera y se invoca paciente.ocuparSala()
     *
     * @param paciente paciente a anunciar
     */
    public void anunciar(Paciente paciente) {
        this.pacientesEspera.add(paciente);
        paciente.ocuparSala();
    }

    /**
     * Retira a un paciente de la sala de espera (privada o patio)
     *
     * <b>pre:</b> paciente no es null <br>
     * <b>post:</b> el paciente queda desocupado de su sala de espera
     *
     * @param paciente paciente a retirar
     * @throws PacienteNoIngresadoException si el paciente no se encuentra en ninguna sala
     */
    public void sacarPacienteSalaEspera(Paciente paciente) throws PacienteNoIngresadoException
    {
        try{
            if(SalaEsperaPrivada.getInstance().getPaciente() == paciente)
                SalaEsperaPrivada.getInstance().desocupar();
            else
                SalaEsperaPatio.getInstance().desocupar(paciente);
        }catch (PacienteNoEncontradoException e){
                throw new PacienteNoIngresadoException(paciente);
        }
    }

    /**
     * Pasa un paciente a atención y lo retira de la sala de espera.
     *
     * <b>pre:</b> paciente no es null <br>
     * <b>post:</b> el paciente figura en pacientesAtencion y ya no ocupa sala de espera
     *
     * @param paciente paciente a atender
     * @throws PacienteNoIngresadoException si no estaba en ninguna sala de espera
     */
    public void atender(Paciente paciente) throws PacienteNoIngresadoException
    {
        if(!this.pacientesAtencion.contains(paciente))
        {
        	this.sacarPacienteSalaEspera(paciente);
            this.pacientesAtencion.add(paciente);
        }
    }

    /**
     * Indica si un paciente ya fue pasado a atención.
     * <b>post:</b> retorna true si está en pacientesAtencion
     *
     * @param paciente paciente a consultar
     * @return true si el paciente está en atención; caso contrario duevuelve false
     */
    public boolean isAtendido(Paciente paciente)
    {
    	return this.pacientesAtencion.contains(paciente);
    }


    /**
     * Egresar a un paciente del sistema de espera/atención.
     *
     * <b>pre:</b>  paciente no es null <br>
     * <b>post:</b> el paciente ya no figura en las estructuras; puede lanzarse excepción según el caso
     *
     * @param paciente paciente a egresar
     * @throws PacienteNoIngresadoException si no estaba en sala de espera al intentar retirarlo
     * @throws PacienteNoEncontradoException si no figura ni en atención ni en espera
     * @throws PacienteNoAtendidoException si estaba esperando (egreso sin haber sido atendido)
     */
    public void egresar(Paciente paciente) throws PacienteNoIngresadoException, PacienteNoEncontradoException, PacienteNoAtendidoException
    {    	
    	
        if (this.isAtendido( paciente ))
            this.pacientesAtencion.remove(paciente);
        else 
        {
            if(this.pacientesEspera.contains(paciente))
            {
                this.pacientesEspera.remove(paciente);
                this.sacarPacienteSalaEspera(paciente);
                
                throw new PacienteNoAtendidoException( paciente ); 
            }
            else
                throw new PacienteNoEncontradoException(paciente);
        }
    }

    /**
     * Obtiene el siguiente paciente a atender (según el menor número de orden)
     * <b>post:</b> retorna el paciente con menor orden o null si no hay
     *
     * @return próximo paciente o null si no hay anunciados
     */
    public Paciente getSigPacienteAtender() {
        Paciente paciente = null;
        if (!this.pacientesEspera.isEmpty())
        {
            paciente = this.pacientesEspera.removeFirst();
        }
        return paciente;
    }
}