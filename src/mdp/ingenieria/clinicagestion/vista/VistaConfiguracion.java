package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VistaConfiguracion {
    private JPanel mainPanel;
    private JButton startBtn;
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;

    public void setActionListener(ActionListener actionListener) {
        startBtn.addActionListener(actionListener);
        textField1.addActionListener(actionListener);
        textField2.addActionListener(actionListener);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getStartBtn() {
        return startBtn;
    }
}
