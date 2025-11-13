package mdp.ingenieria.clinicagestion.controlador;

import mdp.ingenieria.clinicagestion.model.persona.Asociado;
import mdp.ingenieria.clinicagestion.vista.VistaAsociados;
import mdp.ingenieria.clinicagestion.vista.VistaBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorAsociados extends Controlador implements ActionListener {
    private VistaAsociados vista;
    private ArrayList<Asociado> asociados;  // TODO revise asociados class (meant to be the model, probably from the bd)

    private static int MAX_ASOC = 10;

    public ControladorAsociados(ArrayList<Asociado> asociados, VistaAsociados vista){
        super(asociados, vista);
    }

    public void setVista(VistaAsociados vista) {
        this.vista = vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAddButton() || src == vista.getTextField1() || src == vista.getTextField2() || src == vista.getTextField3()) {
            String name = vista.getTextField1().getText().trim();
            String surname = vista.getTextField2().getText().trim();
            String id = vista.getTextField3().getText().trim();

            vista.getTextField1().setBorder(BorderFactory.createLineBorder(Color.GRAY));
            vista.getTextField2().setBorder(BorderFactory.createLineBorder(Color.GRAY));
            vista.getTextField3().setBorder(BorderFactory.createLineBorder(Color.GRAY));

            if (name.isEmpty() || surname.isEmpty() || id.isEmpty()) {
                if (name.isEmpty()) vista.getTextField1().setBorder(BorderFactory.createLineBorder(Color.RED));
                if (surname.isEmpty()) vista.getTextField2().setBorder(BorderFactory.createLineBorder(Color.RED));
                if (id.isEmpty()) vista.getTextField3().setBorder(BorderFactory.createLineBorder(Color.RED));
                popupError("Complete todos los campos");
            } else if (vista.getModel().getRowCount() < MAX_ASOC) {
                vista.addAsociado(name, surname, id);
                vista.getTextField1().setText("");
                vista.getTextField2().setText("");
                vista.getTextField3().setText("");
                vista.getTextField1().requestFocusInWindow();
            } else {
                popupError("Limite de usuarios excedido");
            }
        } else if (src == vista.getRemoveButton()) {
            int selectedRow = vista.getTable().getSelectedRow();
            if (selectedRow != -1) {
                vista.removeAsociado(selectedRow);
            }
        } else if (src == vista.getGenerateBtn()) {
            String[] asoc = vista.generateUser();
            vista.addAsociado(asoc[0], asoc[1], asoc[2]);
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
