package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.model.persona.Operario;
import mdp.ingenieria.clinicagestion.model.persona.Persona;
import mdp.ingenieria.clinicagestion.model.persona.AmbulanciaInteractorFactory;
import mdp.ingenieria.clinicagestion.model.simulation.Actor;
import mdp.ingenieria.clinicagestion.model.simulation.Simulation;
import mdp.ingenieria.clinicagestion.model.simulation.SimulationStateMessage;
import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;
import mdp.ingenieria.clinicagestion.persistence.PersonaDTO;
import mdp.ingenieria.clinicagestion.service.AsociadoService;
import mdp.ingenieria.clinicagestion.util.ThreadUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class ControladorSimulacion extends Controlador implements Observer {
	
    private IVistaConfiguracion vistaConfig;
    
    private IVistaSimulacion vistaSim;
    
    private IVistaBase vistaNav;

    private AsociadoGenerator generator = new AsociadoGenerator();
    private AsociadoService service = new AsociadoService();

    private int numAsoc;
    private int numReq;
    private boolean isGenerated;

    private Operario operarioSimulacion;

    private ArrayList<String> asociadosDnis;

    private ControladorAmbulancia controladorAmbulancia;

    public ControladorSimulacion() {
        Simulation.getInstance().addObserver(this);
        this.asociadosDnis = new ArrayList<>();
        this.controladorAmbulancia = new ControladorAmbulancia( this );
        
        this.operarioSimulacion = (Operario) AmbulanciaInteractorFactory.create(
        		AmbulanciaInteractorFactory.OPERARIO,
        		"Operario Usuario", "-", "-","-", "-",
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

            numAsoc = vistaConfig.getNumAsoc();
            numReq = vistaConfig.getNumReq();
            isGenerated = vistaConfig.getIsGenerated();

            if (numReq > 10)
                vistaConfig.popupError("Número de pedidos por asociado excedido");
            else if (isGenerated && numAsoc > 50) {
                vistaConfig.popupError("Número de asociados excedido");
            } else if (!isGenerated && numAsoc > service.contar()) {
                vistaConfig.popupError("Número de asociados disponibles excedido");
            } else {
                vistaSim.clearSimulation();
                this.initializeSimulation();

                vistaNav.showPage(code);
            }

        } else if (src == vistaSim.getStopBtn()) {
            this.startEndSimulationProtocol();
        } else if (src == vistaSim.getMaintenanceBtn()) {
            this.controladorAmbulancia.eventOperario(operarioSimulacion);
        }
    }
    
    private void startEndSimulationProtocol() {
    	Simulation.getInstance().setTaskTime( 250 );
    	Simulation.getInstance().setStatus( Simulation.STATE_FINALIZING );
    }
    
    private void initializeSimulation() {
    	Simulation.getInstance().setTaskTime( ThreadUtil.MEDIUM );
    	
        List<AsociadoDTO> asociados;
        if (isGenerated)
            asociados = generator.generateUsers(numAsoc);
    	else
            asociados = service.listarRnd(numAsoc);

        PersonaDTO operario = new PersonaDTO("40000111", "Operario Simulado", "2234000000", "Mar del Plata", "Colón 3000");
    
        this.addAsociados( asociados );
        Simulation.getInstance().start(asociados.toArray(new AsociadoDTO[0]), operario, numReq);
    }
    
    private void addAsociados(List<AsociadoDTO> asociados) {
    	   for (AsociadoDTO asociado : asociados) {
               vistaSim.addAsociado(asociado.getNya(), 0);
               this.asociadosDnis.add( asociado.getDni() );
           }
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

    @Override
    public void update(Observable o, Object arg) {
        if (o == Simulation.getInstance()) {
        	SimulationStateMessage simulationMessage = (SimulationStateMessage) arg; 
            String status = simulationMessage.getStatus();
        	
        	if (!Objects.equals(status, Simulation.STATE_TERMINATED) && simulationMessage.hasActorModel()) {
        		Persona persona = simulationMessage.getActorModel();
        		this.updateAsociadoState(persona.getDni(), Actor.TERMINATED);
        	}
            	

            if (status.equals(Simulation.STATE_TERMINATED)) {
                vistaSim.popupMessage("Redirigiendo...", 1500);
                this.asociadosDnis.clear();
                vistaNav.showPage( this.vistaSim.getStopSimulatioActionCommand() );
                // end protocol
            }
        }
    }
}
