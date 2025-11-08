package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilaAsociado extends JPanel {
    private final JLabel nameLabel;
    private final JLabel stateLabel;

    public FilaAsociado(String nombre, String estado) {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.WHITE);
        nameLabel = new JLabel(nombre);
        nameLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        stateLabel = new JLabel(estado);
        stateLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(nameLabel, BorderLayout.WEST);
        add(stateLabel, BorderLayout.EAST);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    }

    public void updateState(String newState, Color color) {
        stateLabel.setText(newState);
        setBackground(color);
        repaint();
    }
}
