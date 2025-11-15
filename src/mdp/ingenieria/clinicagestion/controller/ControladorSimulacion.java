package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.model.simulation.SimulationStateMessage;
import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;
import mdp.ingenieria.clinicagestion.view.VistaBase;
import mdp.ingenieria.clinicagestion.view.VistaConfiguracion;
import mdp.ingenieria.clinicagestion.view.VistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ControladorSimulacion extends Controlador implements Observer {
	
    private IVistaConfiguracion vistaConfig;
    
    private IVistaSimulacion vistaSim;
    
    private IVistaBase vistaNav;

    private int numAsoc;
    private int numReq;

    private Operario operarioSimulacion;

    private ArrayList<String> asociadosDnis;
    
    private ControladorAmbulancia controladorAmbulancia;

    public ControladorSimulacion() {
        Simulation.getInstance().addObserver(this);
        this.asociadosDnis = new ArrayList<>();
        this.controladorAmbulancia = new ControladorAmbulancia( this );
        
        this.operarioSimulacion = (Operario) AmbulanciaInteractorFactory.create(
        		AmbulanciaInteractorFactory.OPERARIO,
        		"Operario Simulado", "-", "-","-", "-",
        		Simulation.getInstance().getAmbulancia()
        );
        
    }

    public void setVistaConfig(IVista vista) {
        this.vistaConfig = (IVistaConfiguracion) vista;
        vista.setActionListener(this);
    }

    public void setVistaSim(IVista vista) {
        this.vistaSim = (IVistaSimulacion) vista;
        vista.setActionListener(this);

        // placeholder(); // todo: remove initialization and set it up from the model
    }
    
    

    public void setVistaNav(IVista vista) {
        this.vistaNav = (IVistaBase) vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String code = e.getActionCommand();

        if (src == vistaConfig.getStartBtn()) {
            try {
                numAsoc = Integer.parseInt(vistaConfig.getTextField1().getText().trim());
                numReq = Integer.parseInt(vistaConfig.getTextField2().getText().trim());
                // check if number is too large

                // todo: configure number of asociados (threads) and requests

                /** simulate simulation call */
                this.initializeSimulation();
                vistaNav.showPage(code);
            } catch (NumberFormatException exc) {
                popupError("Números inválidos");
            }
        } else if (src == vistaSim.getStopBtn()){
            // todo: configure soft simulation stop
            vistaSim.popupMessage("Redirigiendo...", 1500);
            vistaNav.showPage(code);
        } else if (src == vistaSim.getMaintenanceBtn()) {
            this.controladorAmbulancia.eventOperario(operarioSimulacion);
        }
    }
    
    private ArrayList<AsociadoDTO> getSampleData () {
        ArrayList<AsociadoDTO> asociados = new ArrayList<>();

        asociados.add(new AsociadoDTO("30111222", "Juan Pérez", "2235123456", "Mar del Plata", "San Juan 123"));
        asociados.add(new AsociadoDTO("28999888", "María Gómez", "2234234567", "Mar del Plata", "Rivadavia 456"));
        asociados.add(new AsociadoDTO("33444555", "Carlos López", "2235345678", "Batán", "Mitre 789"));
        asociados.add(new AsociadoDTO("27777111", "Laura Fernández", "2235456789", "Mar del Plata", "Independencia 2500"));
        asociados.add(new AsociadoDTO("31222999", "Diego Martínez", "2235567890", "Sierra de los Padres", "Los Pinos 55"));
        
        return asociados;
    }
    
    private void initializeSimulation() {
    	ArrayList<AsociadoDTO> asociados = this.getSampleData(); 
        PersonaDTO operario = new PersonaDTO("40000111", "Operario Simulado", "2234000000", "Mar del Plata", "Colón 3000");
    
        this.addAsociados( asociados );
        Simulation.getInstance().start(asociados.toArray(new AsociadoDTO[0]), operario, numReq);
    }
    
    private void addAsociados(ArrayList<AsociadoDTO> asociados) {
    	   for (AsociadoDTO asociado : asociados) {
               vistaSim.addAsociado(asociado.getNya(), 0);
               this.asociadosDnis.add( asociado.getDni() );
           }
    }

    private void popupError(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    
    public void addOperation( String message ) {
    	vistaSim.addOperation( message ); 
    }
    
    public void updateAsociadoState( String dni, int status ) {
        int index = asociadosDnis.indexOf(dni);
        if (index != -1) {
        	vistaSim.changeAsociadoState(index, status);
        }
    }
    
    public void updateAmbulanciaState( String state ) {
    	vistaSim.changeAmbulanceState( state );
    }

    private void placeholder() {
        String[] asoc = {"Alice", "Bruno", "Carla", "Diego", "Elena", "Fabio", "Greta", "Hugo"};
        String[] acciones = {
                "Atender a un paciente",
                "Buscar a Alice",
                "Ir al taller",
                "Reabastecer oxígeno",
                "Llevar paciente al hospital",
                "Desinfectar la ambulancia",
                "Revisar equipo médico",
                "Reportar al centro de control",
                "Esperar nueva emergencia",
                "Cargar camilla"
        };

        for (String a : asoc) {
            vistaSim.addAsociado(a, 0);
        }

        for (String a : acciones) {
            vistaSim.addOperation(a);
        }  
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == Simulation.getInstance()) {
        	SimulationStateMessage simulationMessage = (SimulationStateMessage) arg; 
            String status = simulationMessage.getStatus();
        	
        	if (status != Simulation.STATE_TERMINATED && simulationMessage.hasActorModel()) {
        		Persona persona = simulationMessage.getActorModel();
        		this.updateAsociadoState(persona.getDni(), Actor.TERMINATED);
        	}
            	

            if (status == Simulation.STATE_TERMINATED) {
                System.out.println("Simulation is now TERMINATED");
                this.asociadosDnis.clear();
                // end protocol
            }
        }
    }
}
