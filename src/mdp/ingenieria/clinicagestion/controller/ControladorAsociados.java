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

    /**
     * Configura la vista asociada a este controlador y realiza una carga inicial
     * de los datos de asociados.
     *
     * <b>pre:</b> vista no debe ser nula y debe ser una instancia compatible con IVistaAsociados <br>
     * <b>post:</b> se inicializa la vista con la lista de asociados existente,
     * se habilitan los botones y se registra este controlador como ActionListener
     *
     * @param vista vista genérica a asociar con este controlador, esperada como IVistaAsociados
     */
    public void setVista(IVista vista) {
        this.vista = (IVistaAsociados) vista;
        this.vista.enableButtons(false);
        this.vista.updateAsociados(service.listar());
        this.vista.enableButtons(true);
        this.vista.setActionListener(this);
    }

    /**
     * Maneja los eventos de la interfaz de usuario asociados a los botones de la vista.
     * Según el origen del evento, realiza operaciones de alta, baja, generación de datos
     * individuales o masivos, y actualización de la tabla de asociados.
     *
     * <b>pre:</b> la vista debe estar configurada mediante setVista y el evento
     * debe provenir de un componente conocido por la vista <br>
     * <b>post:</b> se ejecuta la acción correspondiente al botón presionado,
     * pudiendo modificar la tabla de asociados y la base de datos asociada
     *
     * @param e evento de acción disparado por la interfaz gráfica
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getAddBtn()) {

            List<JTextField> fields = vista.getAllTextFields();
            validateAndAddAsociado(fields);

        } else if (src == vista.getRemoveBtn()) {

            int selectedRow = vista.getTable().getSelectedRow();
            if (selectedRow >= 0 && selectedRow < vista.getModel().getRowCount()) {
                try {
                    service.baja(vista.getIdFromRow(selectedRow));
                    vista.removeAsociadoRow(selectedRow); // or vista.vista.updateAsociados(service.listar())
                } catch (RuntimeException exc) {
                    vista.popupError(exc.getMessage());
                }
            }

        } else if (src == vista.getGenerateBtn()) {

            String[] asociado = generator.generateFields();

            List<JTextField> fields = vista.getAllTextFields();
            boolean allFilled = fields.stream().noneMatch(f -> f.getText().trim().isEmpty());

            if (allFilled) {
                // validateAndAddAsociado(fields); // works the same as addAsociado
                for (int i = 0; i < Math.min(fields.size(), asociado.length); i++) {
                    JTextField f = fields.get(i);
                    f.setText(asociado[i]);
                }
            } else {
                for (int i = 0; i < Math.min(fields.size(), asociado.length); i++) {
                    JTextField f = fields.get(i);
                    f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
                    vista.popupError(exc.getMessage());
                }

        } else if (src == vista.getGenerateTableBtn()) {

            try {
                List<AsociadoDTO> lista = generator.generateUsers(10);
                service.altaTabla(lista);
                vista.updateAsociados(service.listar());
            } catch (RuntimeException exc) {
                vista.popupError(exc.getMessage());
            }

        }
    }

    /**
     * Valida el contenido de los campos de texto de la vista y, si son válidos,
     * crea un nuevo asociado y lo da de alta mediante el servicio.
     *
     * <b>pre:</b> fields debe contener al menos los campos:
     * DNI, nombre, apellido, teléfono, ciudad y dirección, en ese orden <br>
     * <b>post:</b> si todos los campos están completos y son válidos, se crea
     * un nuevo AsociadoDTO, se registra mediante el servicio y se actualiza la vista;
     * en caso contrario se informa el error y no se modifica la lista de asociados
     *
     * @param fields lista de campos de texto que contienen los datos del asociado a crear
     */
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
            vista.popupError("Complete todos los campos");
        } else {
            String id = fields.get(0).getText().trim();
            String name = fields.get(1).getText().trim();
            String surname = fields.get(2).getText().trim();
            String phone = fields.get(3).getText().trim();
            String city = fields.get(4).getText().trim();
            String address = fields.get(5).getText().trim();

            String nya = name + " " + surname;

            try {
                AsociadoDTO newAsociado = new AsociadoDTO(id, nya, phone, city, address);
                service.alta(newAsociado);
                vista.updateAsociados(service.listar()); // or vista.addAsociadoRow(newAsociado)

                for (JTextField f : fields) f.setText("");
                fields.get(0).requestFocusInWindow();
            } catch (RuntimeException exc) {
                vista.popupError(exc.getMessage());
            }
        }
    }
}
