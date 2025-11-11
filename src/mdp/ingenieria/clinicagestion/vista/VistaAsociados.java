package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VistaAsociados {
    private JPanel mainPanel;
    private JTextField nomField;
    private JButton addButton;
    private JButton removeButton;
    private JTable table1;
    private JTextField dniField;
    private JTextField dirField;
    private JButton generateBtn;
    private JTextField apeField;
    private JTextField telField;
    private JTextField ciuField;

    private DefaultTableModel model;
    private Random random = new Random();

    public VistaAsociados() {
        String[] columnNames = {"Nombre", "DNI", "Domicilio"};
        Object[][] data = {};

        table1.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
    }

    public void setActionListener(ActionListener actionListener) {
        nomField.addActionListener(actionListener);
        dniField.addActionListener(actionListener);
        dirField.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
        generateBtn.addActionListener(actionListener);
    }

    public void addAsociado(String name, String id, String address) {
        model.addRow(new Object[]{name, id, address});
    }

    public void removeAsociado(int index) {
        if (index >= 0 && index < model.getRowCount()) {
            model.removeRow(index);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getTable() {
        return table1;
    }

    public JTextField getTextField1() {
        return nomField;
    }

    public JTextField getTextField2() {
        return dniField;
    }

    public JTextField getTextField3() {
        return dirField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public List<JTextField> getAllTextFields() {
        return Arrays.asList(dniField, nomField, apeField, telField, ciuField, dirField);
    }
}
