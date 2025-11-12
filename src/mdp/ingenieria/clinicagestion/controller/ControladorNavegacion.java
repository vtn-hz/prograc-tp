package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.view.VistaBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNavegacion implements ActionListener {
    private VistaBase vista;

    public void setVista(VistaBase vista) {
        this.vista = vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String code = e.getActionCommand();

        this.vista.showPage(code);
    }
}
