package mdp.ingenieria.clinicagestion.vista;

import mdp.ingenieria.clinicagestion.controller.IVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Random;

public class VistaAsociados implements IVista {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton addButton;
    private JButton removeButton;
    private JLabel label1;
    private JTable table1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton generateBtn;

    private DefaultTableModel model;
    private Random random = new Random();

    /**
     * Crea la vista de asociados e inicializa la tabla con sus columnas.
     *
     * <b>post:</b> la tabla queda configurada con columnas "Name", "ID" y "Address"
     *              y lista para recibir filas
     */
    public VistaAsociados() {
        String[] columnNames = {"Name", "ID", "Address"};
        Object[][] data = {};

        table1.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
    }

    /**
     * Registra un ActionListener para todos los componentes interactivos
     *
     * <b>pre:</b> actionListener no debe ser null <br>
     * <b>post:</b> el listener queda asociado a los eventos de la vista
     *
     * @param actionListener manejador de acciones a utilizar
     */
    public void setActionListener(ActionListener actionListener) {
        textField1.addActionListener(actionListener);
        textField2.addActionListener(actionListener);
        textField3.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
        generateBtn.addActionListener(actionListener);
    }

    /**
     * Agrega un asociado a la tabla.
     *
     * <b>pre:</b> name, surname e id no deben ser nulos <br>
     * <b>post:</b> se agrega una nueva fila al modelo de la tabla
     *
     * @param name    nombre del asociado
     * @param surname segundo dato (por ejemplo, apellido)
     * @param id      identificación del asociado
     */
    public void addAsociado(String name, String surname, String id) {
        model.addRow(new Object[]{name, surname, id});
    }

    /**
     * Elimina un asociado de la tabla en el índice indicado.
     *
     * <b>pre:</b> index está entre 0 y el número de filas - 1 <br>
     * <b>post:</b> si el índice es válido, la fila correspondiente se elimina
     *
     * @param index índice de la fila a eliminar
     */
    public void removeAsociado(int index) {
        if (index >= 0 && index < model.getRowCount()) {
            model.removeRow(index);
        }
    }

    /**
     * @return panel principal de la vista
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @return tabla de asociados
     */
    public JTable getTable() {
        return table1;
    }

    /**
     * @return primer JTextField
     */
    public JTextField getTextField1() {
        return textField1;
    }

    /**
     * @return segundo JTextField
     */
    public JTextField getTextField2() {
        return textField2;
    }

    /**
     * @return tercer JTextField
     */
    public JTextField getTextField3() {
        return textField3;
    }

    /**
     * @return boton de alta de asociado
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * @return boton de baja de asociado
     */
    public JButton getRemoveButton() {
        return removeButton;
    }

    /**
     * @return boton de generacion aleatoria
     */
    public JButton getGenerateBtn() {
        return generateBtn;
    }

    /**
     * @return modelo de la tabla de asociados
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Genera datos aleatorios de un usuario de ejemplo.
     *
     * <b>post:</b> se devuelve un arreglo con nombre, id y city simulados
     *
     * @return arreglo de longitud 3: [name, id, city]
     */
    public String[] generateUser() {
        char letter = (char) ('A' + random.nextInt(26));
        String name = "Asociado " + letter;
        String id = String.format("%08d", random.nextInt(99999999));
        String city = "" + (char)('A' + random.nextInt(26)) +
                (char)('A' + random.nextInt(26)) +
                (char)('A' + random.nextInt(26));
        return new String[]{name, id, city};
    }
}
