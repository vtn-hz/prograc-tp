package mdp.ingenieria.clinicagestion.controlador;

import mdp.ingenieria.clinicagestion.vista.VistaAsociados;
import mdp.ingenieria.clinicagestion.vista.VistaSimulacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSimulacion implements ActionListener {
    private VistaSimulacion vista;

    public void setVista(VistaSimulacion vista) {
        this.vista = vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAtencionBtn()) {

        } else if (src == vista.getTrasladoBtn()) {

        }
    }
}
