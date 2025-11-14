package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class ControladorAsociados extends Controlador {
    private IVistaAsociados vista;
    private AsociadoGenerator generator = new AsociadoGenerator();

    public void setVista(IVista vista) {
        this.vista = (IVistaAsociados) vista;
        vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAddBtn()) {

            List<JTextField> fields = vista.getAllTextFields();
            validateAndAddAsociado(fields);

        } else if (src == vista.getRemoveBtn()) {

            int selectedRow = vista.getTable().getSelectedRow();
            if (selectedRow >= 0 && selectedRow < vista.getModel().getRowCount()) {
                // todo: remove one Asociado from database with dao.eliminarAsociado(vista.getIdFromRow(selectedRow))
                vista.removeAsociadoRow(selectedRow); // or vista.updateAsociados(List<AsociadoDTO>)
            }

        } else if (src == vista.getGenerateBtn()) {

            String[] asociado = generator.generateFields();

            List<JTextField> fields = vista.getAllTextFields();
            boolean allFilled = fields.stream().noneMatch(f -> f.getText().trim().isEmpty());

            if (allFilled) {
                validateAndAddAsociado(fields); // works the same as addAsociado
            } else {
                for (int i = 0; i < Math.min(fields.size(), asociado.length); i++) {
                    JTextField f = fields.get(i);
                    if (f.getText().trim().isEmpty()) {
                        f.setText(asociado[i]);
                    }
                }
            }

        } else if (src == vista.getRemoveTableBtn()) {

            // todo: remove all Asociados from database (¿dao.eliminarTabla(...)?)
            vista.deleteAsociados(); // or vista.updateAsociados(List<AsociadoDTO>)

        } else if (src == vista.getGenerateTableBtn()) {

            List<AsociadoDTO> lista = generator.generateUsers(20);
            // todo: add all Asociados to database (¿dao.crearTabla(...)?)
            vista.updateAsociados(lista);

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

            AsociadoDTO asociado = new AsociadoDTO(id, nya, phone, city, address);
            // todo: add one Asociado to database with dao.agregarAsociado(asociado)
            vista.addAsociadoRow(asociado); // or vista.updateAsociados(List<AsociadoDTO>)

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
