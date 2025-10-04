package sistema.gestion.clinica.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GestorAtencionPaciente {
    private HashMap<Integer,Paciente> pacientesEspera; //integer para el numero de orden
    private ArrayList<Paciente> pacientesAtencion;
    private int ultimoNroOrden;

    public GestorAtencionPaciente() {
        this.pacientesEspera = new HashMap<Integer,Paciente>();
        this.pacientesAtencion = new ArrayList<Paciente>();
        ultimoNroOrden=0;
    }

    public void anunciar(Paciente paciente) {
        this.pacientesEspera.put(ultimoNroOrden,paciente);
        ultimoNroOrden+=1;
        SalaEsperaPatio.getInstance().ocupar(paciente);
    }

    public void atender(Paciente paciente) {
        this.pacientesAtencion.add(paciente);
    }

    public void egresar(Paciente paciente) {
        if (this.pacientesAtencion.contains(paciente))
        {
            this.pacientesAtencion.remove(paciente);
        }
        else{
            if(this.pacientesEspera.containsValue(paciente))
            {
                pacientesEspera.entrySet().removeIf(entry -> entry.getValue().equals(paciente));
                SalaEsperaPatio.getInstance().desocupar(paciente);
            }
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