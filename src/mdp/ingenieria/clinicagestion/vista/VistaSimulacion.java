package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VistaSimulacion {
    private JPanel mainPanel;
    private JButton stopBtn;
    private JLabel label1;
    private JList list1;
    private JButton atencionBtn;
    private JButton trasladoBtn;

    public void setActionListener(ActionListener actionListener) {
        atencionBtn.addActionListener(actionListener);
        trasladoBtn.addActionListener(actionListener);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getAtencionBtn() {
        return atencionBtn;
    }

    public JButton getTrasladoBtn() {
        return trasladoBtn;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
