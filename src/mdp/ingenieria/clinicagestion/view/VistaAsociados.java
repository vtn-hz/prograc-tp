package mdp.ingenieria.clinicagestion.view;

import mdp.ingenieria.clinicagestion.controller.IVistaAsociados;
import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VistaAsociados implements IVistaAsociados {
    private JPanel mainPanel;
    private JTextField nomField;
    private JTextField apeField;
    private JTextField dniField;
    private JTextField telField;
    private JTextField ciuField;
    private JTextField dirField;
    private JButton addBtn;
    private JButton removeBtn;
    private JTable table1;
    private JButton generateBtn;
    private JButton removeTableBtn;
    private JButton generateTableBtn;
    private JScrollPane scrollPane;

    private DefaultTableModel model;
    private Random random = new Random();

    /**
     * Inicializa la vista, configurando el modelo de la tabla y sus columnas.
     *
     * <b>post:</b> la tabla queda lista para recibir filas de asociados
     */
    public VistaAsociados() {
        String[] columnNames = {"Nombre", "DNI", "Teléfono", "Domicilio"};
        Object[][] data = {};

        table1.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
    }

    /**
     * Registra un ActionListener para los botones y campos interactivos.
     *
     * @param actionListener listener a asociar a los componentes de la vista
     */
    public void setActionListener(ActionListener actionListener) {
        nomField.addActionListener(actionListener);
        dniField.addActionListener(actionListener);
        dirField.addActionListener(actionListener);
        addBtn.addActionListener(actionListener);
        removeBtn.addActionListener(actionListener);
        generateBtn.addActionListener(actionListener);
        removeTableBtn.addActionListener(actionListener);
        generateTableBtn.addActionListener(actionListener);
    }

    /**
     * Actualiza la tabla de asociados reemplazando completamente su contenido.
     *
     * <b>pre:</b> lista no debe ser null <br>
     * <b>post:</b> la tabla muestra exactamente los elementos de lista
     *
     * @param lista lista de asociados a mostrar
     */
    @Override
    public void updateAsociados(List<AsociadoDTO> lista) {
        deleteAsociados();
        for (AsociadoDTO a : lista) {
            addAsociadoRow(a);
        }
    }

    /**
     * Obtiene el DNI de la fila seleccionada.
     *
     * <b>pre:</b> selectedRow es un índice válido de la tabla <br>
     * <b>post:</b> se devuelve el DNI correspondiente
     *
     * @param selectedRow índice de la fila seleccionada
     * @return DNI contenido en la fila
     */
    @Override
    public String getIdFromRow(int selectedRow) {
        return model.getValueAt(selectedRow, 1).toString();
    }

    /**
     * Elimina todas las filas de la tabla.
     *
     * <b>post:</b> la tabla queda vacía
     */
    @Override
    public void deleteAsociados() {
        model.setRowCount(0);
    }

    /**
     * Agrega una fila a la tabla con la información del asociado.
     *
     * <b>pre:</b> dto no debe ser null <br>
     * <b>post:</b> la tabla contiene una nueva fila con los datos del asociado
     *
     * @param dto datos del asociado a insertar
     */
    @Override
    public void addAsociadoRow(AsociadoDTO dto) {
        model.addRow(new Object[]{
                dto.getNya(),
                dto.getDni(),
                dto.getTelefono(),
                dto.getDireccion() + ", " + dto.getCiudad()
        });
    }

    /**
     * Elimina una fila específica de la tabla.
     *
     * <b>pre:</b> rowNumber es un índice válido <br>
     * <b>post:</b> esa fila ya no existe en la tabla
     *
     * @param rowNumber índice de la fila a eliminar
     */
    @Override
    public void removeAsociadoRow(int rowNumber) {
        model.removeRow(rowNumber);
    }

    /**
     * Muestra un mensaje emergente de error.
     *
     * @param message texto del error a mostrar
     */
    @Override
    public void popupError(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Solicita confirmación del usuario para eliminar todos los asociados.
     *
     * @return true si el usuario confirma; false en caso contrario
     */
    @Override
    public boolean confirmDeleteAll() {
        int r = JOptionPane.showConfirmDialog(
                scrollPane,
                "¿Eliminar todos los asociados?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return r == JOptionPane.YES_OPTION;
    }

    /**
     * Habilita o deshabilita todos los botones de la vista.
     *
     * @param enabled estado deseado de los botones
     */
    @Override
    public void enableButtons(boolean enabled) {
        addBtn.setEnabled(enabled);
        generateBtn.setEnabled(enabled);
        generateTableBtn.setEnabled(enabled);
        removeBtn.setEnabled(enabled);
        removeTableBtn.setEnabled(enabled);
    }

    /**
     * @return tabla donde se visualizan los asociados.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @return tabla donde se visualizan los asociados.
     */
    public JTable getTable() {
        return table1;
    }

    /**
     * @return botón para agregar un asociado.
     */
    public JButton getAddBtn() {
        return addBtn;
    }

    /**
     * @return botón para eliminar un asociado seleccionado.
     */
    public JButton getRemoveBtn() {
        return removeBtn;
    }

    /**
     * @return botón para generar campos de asociado aleatorios.
     */
    public JButton getGenerateBtn() {
        return generateBtn;
    }

    /**
     * @return botón para eliminar toda la tabla.
     */
    public JButton getRemoveTableBtn() {
        return removeTableBtn;
    }

    /**
     * @return botón para generar una tabla completa aleatoria.
     */
    public JButton getGenerateTableBtn() {
        return generateTableBtn;
    }

    /**
     * @return modelo asociado a la tabla.
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Obtiene todos los campos de texto del formulario.
     *
     * @return lista de JTextField en orden lógico de uso
     */
    public List<JTextField> getAllTextFields() {
        return Arrays.asList(dniField, nomField, apeField, telField, ciuField, dirField);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setName("Page1");
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setName("");
        label1.setText("Page 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel1.add(label1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel2, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("DNI");
        panel2.add(label2, BorderLayout.NORTH);
        dniField = new JTextField();
        dniField.setPreferredSize(new Dimension(150, 20));
        panel2.add(dniField, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel3, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Nombre");
        panel3.add(label3, BorderLayout.NORTH);
        nomField = new JTextField();
        nomField.setColumns(0);
        nomField.setPreferredSize(new Dimension(150, 20));
        panel3.add(nomField, BorderLayout.CENTER);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel4, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Apellido");
        panel4.add(label4, BorderLayout.NORTH);
        apeField = new JTextField();
        apeField.setColumns(0);
        apeField.setPreferredSize(new Dimension(150, 20));
        panel4.add(apeField, BorderLayout.CENTER);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel5, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Teléfono");
        panel5.add(label5, BorderLayout.NORTH);
        telField = new JTextField();
        telField.setColumns(0);
        telField.setPreferredSize(new Dimension(150, 20));
        panel5.add(telField, BorderLayout.CENTER);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel6, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Ciudad");
        panel6.add(label6, BorderLayout.NORTH);
        ciuField = new JTextField();
        ciuField.setColumns(0);
        ciuField.setPreferredSize(new Dimension(150, 20));
        panel6.add(ciuField, BorderLayout.CENTER);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(panel7, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Dirección");
        panel7.add(label7, BorderLayout.NORTH);
        dirField = new JTextField();
        dirField.setPreferredSize(new Dimension(150, 20));
        panel7.add(dirField, BorderLayout.CENTER);
        addBtn = new JButton();
        addBtn.setText("Añadir");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel1.add(addBtn, gbc);
        scrollPane = new JScrollPane();
        scrollPane.setAlignmentX(1.0f);
        scrollPane.setAlignmentY(1.0f);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);
        table1 = new JTable();
        scrollPane.setViewportView(table1);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel8, gbc);
        removeBtn = new JButton();
        removeBtn.setText("Eliminar");
        panel8.add(removeBtn);
        removeTableBtn = new JButton();
        removeTableBtn.setText("Eliminar Tabla");
        panel8.add(removeTableBtn);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel9, gbc);
        generateBtn = new JButton();
        generateBtn.setActionCommand("Generar");
        generateBtn.setLabel("Generar");
        generateBtn.setText("Generar");
        panel9.add(generateBtn);
        generateTableBtn = new JButton();
        generateTableBtn.setActionCommand("Generar");
        generateTableBtn.setLabel("Generar Tabla");
        generateTableBtn.setText("Generar Tabla");
        panel9.add(generateTableBtn);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
