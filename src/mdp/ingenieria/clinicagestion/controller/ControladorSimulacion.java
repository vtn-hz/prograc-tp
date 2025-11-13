package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.view.VistaBase;
import mdp.ingenieria.clinicagestion.view.VistaConfiguracion;
import mdp.ingenieria.clinicagestion.view.VistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSimulacion extends Controlador {
    private IVistaConfiguracion vistaConfig;
    private IVistaSimulacion vistaSim;
    private IVistaBase vistaNav;

    private int numAsoc;
    private int numReq;

    public void setVistaConfig(IVista vista) {
        this.vistaConfig = (IVistaConfiguracion) vista;
        vista.setActionListener(this);
    }

    public void setVistaSim(IVista vista) {
        this.vistaSim = (IVistaSimulacion) vista;
        vista.setActionListener(this);

        placeholder(); // todo: remove initialization and set it up from the model
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
                vistaNav.showPage(code);
            } catch(NumberFormatException exc) {
                popupError("Números inválidos");
            }
        }

        if (src == vistaSim.getMaintenanceBtn()) {
            // todo: configure user interaction in simulation
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

        vistaSim.changeAsociadoState(1, 1);
        vistaSim.changeAsociadoState(3, 2);
        vistaSim.changeAsociadoState(5, 3);

        vistaSim.changeAmbulanceState("En camino a la clínica");
    }
}
