package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Random;

public class VistaAsociados {
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

    public VistaAsociados() {
        String[] columnNames = {"Name", "ID", "Address"};
        Object[][] data = {};

        table1.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);
    }

    public void setActionListener(ActionListener actionListener) {
        textField1.addActionListener(actionListener);
        textField2.addActionListener(actionListener);
        textField3.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
        generateBtn.addActionListener(actionListener);
    }

    public void addAsociado(String name, String surname, String id) {
        model.addRow(new Object[]{name, surname, id});
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
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
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
