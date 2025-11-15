package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;
import mdp.ingenieria.clinicagestion.service.AsociadoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ControladorAsociados extends Controlador {
    private IVistaAsociados vista;
    private AsociadoGenerator generator = new AsociadoGenerator();
    private AsociadoService service = new AsociadoService();

    public void setVista(IVista vista) {
        this.vista = (IVistaAsociados) vista;
        this.vista.enableButtons(false);
        this.vista.updateAsociados(service.listar());
        this.vista.enableButtons(true);
        this.vista.setActionListener(this);
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
                service.baja(vista.getIdFromRow(selectedRow));
                vista.removeAsociadoRow(selectedRow); // or vista.vista.updateAsociados(service.listar())
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

            if (vista.confirmDeleteAll())
                try {
                    service.bajaTabla();
                    vista.deleteAsociados(); // or vista.updateAsociados(service.listar())
                } catch (RuntimeException exc) {
                    vista.popupError("Error eliminando la tabla:\n" + exc.getMessage());
                }

        } else if (src == vista.getGenerateTableBtn()) {

            List<AsociadoDTO> lista = generator.generateUsers(10);
            service.altaTabla(lista);
            vista.updateAsociados(service.listar());

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

            AsociadoDTO newAsociado = new AsociadoDTO(id, nya, phone, city, address);
            service.alta(newAsociado);
            vista.updateAsociados(service.listar()); // or vista.addAsociadoRow(newAsociado)

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
