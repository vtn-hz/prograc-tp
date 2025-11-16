package mdp.ingenieria.clinicagestion.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilaAsociado extends JPanel {
    private final JLabel nameLabel;
    private final JLabel stateLabel;

    /**
     * Crea una fila visual con nombre y estado inicial del asociado.
     *
     * <b>pre:</b> nombre y estado no deben ser null <br>
     * <b>post:</b> el panel queda inicializado y listo para mostrarse en la vista
     *
     * @param nombre nombre del asociado
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
     * <b>pre:</b> newState no debe ser null <br>
     * <b>post:</b> se muestra el nuevo estado y el fondo cambia a la tonalidad indicada
     *
     * @param newState nuevo estado textual
     * @param color    color de fondo representativo
     */
    public void updateState(String newState, Color color) {
        stateLabel.setText(newState);
        setBackground(color);
        repaint();
    }
}
