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

    /**
     * Crea un controlador de simulación, se registra como observador de la simulación
     * y configura el controlador de ambulancia y el operario de simulación.
     *
     * <b>pre:</b> la clase Simulation debe estar correctamente inicializada
     * y proveer una instancia de ambulancia <br>
     * <b>post:</b> el controlador queda registrado como observador de la simulación,
     * se inicializa la lista de DNIs, se crea el controlador de ambulancia
     * y el operario de simulación asociado a la ambulancia
     */
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

    /**
     * Asocia la vista de configuración a este controlador.
     *
     * <b>pre:</b> vista no debe ser nula y debe implementar IVistaConfiguracion <br>
     * <b>post:</b> la vista de configuración queda referenciada y lista para enviar
     * eventos de acción al controlador
     *
     * @param vista vista genérica que se interpreta como IVistaConfiguracion
     */
    public void setVistaConfig(IVista vista) {
        this.vistaConfig = (IVistaConfiguracion) vista;
        vista.setActionListener(this);
    }

    /**
     * Asocia la vista de simulación a este controlador.
     *
     * <b>pre:</b> vista no debe ser nula y debe implementar IVistaSimulacion <br>
     * <b>post:</b> la vista de simulación queda referenciada y lista para enviar
     * eventos de acción al controlador
     *
     * @param vista vista genérica que se interpreta como IVistaSimulacion
     */
    public void setVistaSim(IVista vista) {
        this.vistaSim = (IVistaSimulacion) vista;
        vista.setActionListener(this);
    }

    /**
     * Asocia la vista base de navegación a este controlador.
     *
     * <b>pre:</b> vista no debe ser nula y debe implementar IVistaBase <br>
     * <b>post:</b> la vista de navegación queda referenciada y lista para enviar
     * eventos de acción al controlador
     *
     * @param vista vista genérica que se interpreta como IVistaBase
     */
    public void setVistaNav(IVista vista) {
        this.vistaNav = (IVistaBase) vista;
        vista.setActionListener(this);
    }

    /**
     * Maneja los eventos de la interfaz relacionados con el inicio, detención
     * de la simulación y solicitudes de mantenimiento.
     *
     * <b>pre:</b> las vistas correspondientes deben haber sido configuradas
     * previamente mediante los métodos setVista... <br>
     * <b>post:</b> según el origen del evento, se valida la configuración,
     * se inicializa o detiene la simulación, o se envía una solicitud de
     * mantenimiento a la ambulancia
     *
     * @param e evento de acción recibido desde alguna de las vistas
     */
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

    /**
     * Inicia el protocolo de finalización de la simulación,
     * ajustando el tiempo de tarea y cambiando el estado de la simulación.
     *
     * <b>pre:</b> la simulación debe estar en ejecución o en un estado
     * que permita ser finalizada <br>
     * <b>post:</b> el estado de la simulación pasa a STATE_FINALIZING
     * y el tiempo de cada tarea se ajusta a un valor específico
     */
    private void startEndSimulationProtocol() {
    	Simulation.getInstance().setTaskTime( 250 );
    	Simulation.getInstance().setStatus( Simulation.STATE_FINALIZING );
    }

    /**
     * Inicializa la simulación creando o seleccionando los asociados,
     * configurando el operario y arrancando la lógica interna de Simulation.
     *
     * <b>pre:</b> vistaSim debe estar configurada, numAsoc, numReq e isGenerated
     * deben haber sido establecidos desde la vista de configuración <br>
     * <b>post:</b> se cargan los asociados en la vista de simulación,
     * se prepara el operario y se inicia la simulación con los parámetros indicados
     */
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

    /**
     * Agrega los asociados proporcionados a la vista de simulación y registra
     * sus DNIs internamente para permitir la actualización posterior de estados.
     *
     * <b>pre:</b> vistaSim debe estar configurada y asociados no debe ser nulo <br>
     * <b>post:</b> la vista muestra a todos los asociados con estado inicial 0
     * y sus DNIs quedan registrados en la lista interna
     *
     * @param asociados lista de asociados a incorporar en la simulación
     */
    private void addAsociados(List<AsociadoDTO> asociados) {
    	   for (AsociadoDTO asociado : asociados) {
               vistaSim.addAsociado(asociado.getNya(), 0);
               this.asociadosDnis.add( asociado.getDni() );
           }
    }

    /**
     * Registra un mensaje de operación en la vista de simulación.
     *
     * <b>pre:</b> vistaSim debe estar configurada y message no debe ser nulo ni vacío <br>
     * <b>post:</b> el mensaje se agrega al registro de operaciones de la simulación
     *
     * @param message descripción textual de la operación realizada
     */
    public void addOperation( String message ) {
    	vistaSim.addOperation( message ); 
    }

    /**
     * Actualiza el estado visual de un asociado a partir de su DNI y del código de estado.
     *
     * <b>pre:</b> dni no debe ser nulo ni vacío, y status debe ser un valor válido
     * según la lógica de Actor <br>
     * <b>post:</b> si el DNI existe en la lista interna, se actualiza el estado del asociado
     * correspondiente en la vista de simulación
     *
     * @param dni    DNI del asociado cuyo estado se desea actualizar
     * @param status nuevo estado del asociado
     */
    public void updateAsociadoState( String dni, int status ) {
        int index = asociadosDnis.indexOf(dni);
        if (index != -1) {
        	vistaSim.changeAsociadoState(index, status);
        }
    }

    /**
     * Actualiza el estado visual de la ambulancia en la vista de simulación.
     *
     * <b>pre:</b> vistaSim debe estar configurada y state no debe ser nulo <br>
     * <b>post:</b> la vista de simulación refleja el estado actual de la ambulancia
     *
     * @param state cadena que representa el nuevo estado de la ambulancia
     */
    public void updateAmbulanciaState( String state ) {
    	vistaSim.changeAmbulanceState( state );
    }

    /**
     * Gestiona las notificaciones de cambio de estado de la simulación.
     * Actualiza el estado de los actores finalizados y controla el flujo al
     * finalizar la simulación.
     *
     * <b>pre:</b> o debe ser la instancia de Simulation y arg debe ser un
     * SimulationStateMessage válido <br>
     * <b>post:</b> si hay actor asociado, se marca como terminado en la vista;
     * si la simulación llega a STATE_TERMINATED, se muestra un mensaje,
     * se limpian los DNIs registrados y se navega a la pantalla de finalización
     *
     * @param o   observable que emite la notificación
     * @param arg mensaje con el estado actual de la simulación
     */
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
