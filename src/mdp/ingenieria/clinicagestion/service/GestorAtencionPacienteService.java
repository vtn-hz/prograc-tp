package mdp.ingenieria.clinicagestion.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import mdp.ingenieria.clinicagestion.exception.PacienteNoAtendidoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoEncontradoException;
import mdp.ingenieria.clinicagestion.exception.PacienteNoIngresadoException;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPatio;
import mdp.ingenieria.clinicagestion.model.clinica.salaespera.SalaEsperaPrivada;
import mdp.ingenieria.clinicagestion.model.persona.Paciente;

public class GestorAtencionPacienteService {
    private HashMap<Integer,Paciente> pacientesEspera; //integer para el numero de orden
    private ArrayList<Paciente> pacientesAtencion;
    private int ultimoNroOrden;

    public GestorAtencionPacienteService() {
        this.pacientesEspera = new HashMap<Integer,Paciente>();
        this.pacientesAtencion = new ArrayList<Paciente>();
        ultimoNroOrden=0;
    }

    public void anunciar(Paciente paciente) {
        this.pacientesEspera.put(this.ultimoNroOrden++, paciente);
        paciente.ocuparSala();
    }
    
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

    public void atender(Paciente paciente) throws PacienteNoIngresadoException
    {
        if(!this.pacientesAtencion.contains(paciente))
        {
            this.pacientesAtencion.add(paciente);
            sacarPacienteSalaEspera(paciente);
        }
    }
    
    public boolean isAtendido(Paciente paciente)
    {
    	return this.pacientesAtencion.contains(paciente);
    }
    
   
    /**
     * LO MODIFIQUE VER MARI, [VALENTINO]
     * */
    
    public void egresar(Paciente paciente) throws PacienteNoIngresadoException, PacienteNoEncontradoException, PacienteNoAtendidoException
    {
        if (this.isAtendido( paciente ))
            this.pacientesAtencion.remove(paciente);
        else{
            if(this.pacientesEspera.containsValue(paciente))
            {
                pacientesEspera.entrySet().removeIf(entry -> entry.getValue().equals(paciente));
                sacarPacienteSalaEspera(paciente);
                
                throw new PacienteNoAtendidoException( paciente ); 
            }
            else
                throw new PacienteNoEncontradoException(paciente);
        }
    }

    public Paciente getSigPacienteAtender() {
        Paciente paciente = null;
        if (!this.pacientesEspera.isEmpty())
        {
            int menorClave = Collections.min(pacientesEspera.keySet());
            paciente = pacientesEspera.get(menorClave);
        }
        return paciente;
    }
}