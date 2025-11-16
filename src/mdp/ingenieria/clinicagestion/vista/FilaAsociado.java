package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilaAsociado extends JPanel {
    private final JLabel nameLabel;
    private final JLabel stateLabel;

    /**
     * Crea una fila visual con el nombre y el estado inicial del asociado.
     *
     * <b>pre:</b> nombre y estado no deben ser nulos <br>
     * <b>post:</b> el panel queda configurado con ambas etiquetas y un alto máximo fijo
     *
     * @param nombre nombre (o nombre y apellido) del asociado
     * @param estado estado inicial a mostrar
     */
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

    /**
     * Actualiza el estado visual del asociado.
     *
     * <b>pre:</b> newState no debe ser nulo; color no debe ser nulo <br>
     * <b>post:</b> se actualiza el texto de estado y el fondo del panel con el color indicado
     *
     * @param newState nuevo texto de estado
     * @param color    color de fondo representativo del estado
     */
    public void updateState(String newState, Color color) {
        stateLabel.setText(newState);
        setBackground(color);
        repaint();
    }
}
