package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaSimulacion {
    private JPanel mainPanel;
    private JButton stopBtn;
    private JPanel panel1;
    private JPanel panel2;
    private JButton maintenanceBtn;
    private JLabel ambulanceLbl;

    String[] states = {
            "Tranquilito en casa",
            "Llamando ambulancia",
            "En traslado",
            "En tratamiento"
    };

    Color[] colors = {
            Color.WHITE,
            new Color(180, 246, 100),
            new Color(255, 229, 143),
            new Color(246, 112, 100)
    };

    public VistaSimulacion() {
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        ambulanceLbl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(122, 138, 153), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    public void setActionListener(ActionListener actionListener) {
        maintenanceBtn.addActionListener(actionListener);
    }

    public void addAsociado(String nya, int state) {
        FilaAsociado row = new FilaAsociado(nya, states[state]);
        panel1.add(row);
    }

    public void changeAsociadoState(int i, int state) {
        FilaAsociado row = (FilaAsociado) panel1.getComponent(i);
        row.updateState(states[state], colors[state]);
        row.setBackground(colors[state]);
    }

    public void addOperation(String msg) {
        JLabel label = new JLabel(msg);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel2.add(label);
    }

    public void changeAmbulanceState(String state) {
        ambulanceLbl.setText(state);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getMaintenanceBtn() {
        return maintenanceBtn;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
