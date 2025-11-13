package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.view.VistaBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNavegacion extends Controlador {
    private IVistaBase vista;

    public void setVista(IVista vista) {
        this.vista = (IVistaBase) vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String code = e.getActionCommand();

        this.vista.showPage(code);
    }
}
