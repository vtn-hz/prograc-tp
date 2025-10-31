package mdp.ingenieria.clinicagestion.controlador;

import mdp.ingenieria.clinicagestion.vista.VistaAsociados;
import mdp.ingenieria.clinicagestion.vista.VistaBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAsociados implements ActionListener {
    private VistaAsociados vista;

    private static int MAX_ASOC = 10;

    public void setVista(VistaAsociados vista) {
        this.vista = vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAddButton() || src == vista.getTextField1()) {
            String text = vista.getTextField1().getText().trim();
            if (vista.getModel().size() < MAX_ASOC) {
                if (!text.isEmpty()) {
                    vista.addAsociado(text);
                    vista.getTextField1().setText("");
                    vista.getTextField1().requestFocusInWindow();
                }
            } else {
                popupError("Limite de usuarios excedido");
            }
        } else if (src == vista.getRemoveButton()) {
            int index = vista.getList1().getSelectedIndex();
            if (index != -1) {
                vista.removeAsociado(index);
            }
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
}
