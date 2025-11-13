package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.view.VistaAsociados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorAsociados extends Controlador {
    private IVistaAsociados vista;

    public void setVista(IVista vista) {
        this.vista = (IVistaAsociados) vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAddButton()) {
            List<JTextField> fields = vista.getAllTextFields();
            validateAndAddAsociado(fields);
        } else if (src == vista.getRemoveButton()) {
            int selectedRow = vista.getTable().getSelectedRow();
            if (selectedRow != -1) {
                // todo: replace removeAsociado with delete from database and refresh view
                vista.removeAsociado(selectedRow);
            }
        } else if (src == vista.getGenerateBtn()) {
            AsociadoGenerator gen = new AsociadoGenerator();
            String[] asoc = gen.generateUser();

            List<JTextField> fields = vista.getAllTextFields();

            boolean allFilled = fields.stream().noneMatch(f -> f.getText().trim().isEmpty());

            if (allFilled) {
                validateAndAddAsociado(fields);
            } else {
                for (int i = 0; i < Math.min(fields.size(), asoc.length); i++) {
                    JTextField f = fields.get(i);
                    if (f.getText().trim().isEmpty()) {
                        f.setText(asoc[i]);
                    }
                }
            }
        }
    }

    private void validateAndAddAsociado(List<JTextField> fields) {
        boolean emptyFound = false;

        for (JTextField f : fields) {
            f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            if (f.getText().trim().isEmpty()) {
                f.setBorder(BorderFactory.createLineBorder(Color.RED));
                emptyFound = true;
            }
        }

        if (emptyFound) {
            popupError("Complete todos los campos");
        } else {
            String id = fields.get(0).getText().trim();
            String name = fields.get(1).getText().trim();
            String surname = fields.get(2).getText().trim();
            String phone = fields.get(3).getText().trim();
            String city = fields.get(4).getText().trim();
            String address = fields.get(5).getText().trim();

            String nya = name + " " + surname;
            String loc = address + ", " + city;

            // todo: replace addAsociado with add to database and refresh view
            vista.addAsociado(nya, id, loc);

            for (JTextField f : fields) f.setText("");
            fields.get(0).requestFocusInWindow();
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
