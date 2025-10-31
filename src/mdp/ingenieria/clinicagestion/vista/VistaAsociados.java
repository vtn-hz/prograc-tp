package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public class VistaAsociados {
    private JPanel mainPanel;
    private JTextField textField1;
    private JList list1;
    private JButton addButton;
    private JButton removeButton;
    private JLabel label1;

    private DefaultListModel<String> model;

    public VistaAsociados() {
        model = new DefaultListModel<>();
        list1.setModel(model);
    }

    public void setActionListener(ActionListener actionListener) {
        textField1.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
    }

    public void addAsociado(String asoc) {
        model.addElement(asoc);
    }

    public void removeAsociado(int index) {
        if (index >= 0 && index < model.size()) {
            model.remove(index);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JList getList1() {
        return list1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public DefaultListModel<String> getModel() {
        return model;
    }
}
