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
    private JLabel label1;
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

    /**
     * Constructor.
     *
     * <b>post:</b> paneles internos configurados con layout vertical para representar listas dinámicas.
     */
    public VistaSimulacion() {
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    }

    /**
     * Asigna el ActionListener a los botones interactivos de la vista.
     *
     * <b>pre:</b> actionListener no es null <br>
     * <b>post:</b> el botón de mantenimiento queda asociado al listener
     *
     * @param actionListener listener de eventos de usuario
     */
    public void setActionListener(ActionListener actionListener) {
        maintenanceBtn.addActionListener(actionListener);
    }

    /**
     * Agrega una fila visual representando a un asociado en un estado inicial.
     *
     * <b>pre:</b> state es un índice válido del arreglo states <br>
     * <b>post:</b> se agrega una fila al panel de asociados
     *
     * @param nya   nombre del asociado
     * @param state índice del estado inicial
     */
    public void addAsociado(String nya, int state) {
        FilaAsociado row = new FilaAsociado(nya, states[state]);
        panel1.add(row);
    }

    /**
     * Actualiza el estado visual de un asociado previamente agregado.
     *
     * <b>pre:</b> i es un índice válido dentro de panel1; state dentro del arreglo states <br>
     * <b>post:</b> se actualizan texto y colores del asociado
     *
     * @param i     índice del asociado en el panel
     * @param state nuevo estado a aplicar
     */
    public void changeAsociadoState(int i, int state) {
        FilaAsociado row = (FilaAsociado) panel1.getComponent(i);
        row.updateState(states[state], colors[state]);
        row.setBackground(colors[state]);
    }

    /**
     * Registra una operación realizada por la ambulancia durante la simulación.
     *
     * <b>pre:</b> msg no debe ser null <br>
     * <b>post:</b> se agrega una etiqueta al panel de historial
     *
     * @param msg texto descriptivo de la operación
     */
    public void addOperation(String msg) {
        JLabel label = new JLabel(msg);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel2.add(label);
    }

    /**
     * Actualiza el texto que representa el estado de la ambulancia.
     *
     * @param state texto del estado actual
     */
    public void changeAmbulanceState(String state) {
        ambulanceLbl.setText(state);
    }

    /**
     * Devuelve el panel raíz.
     *
     * @return panel principal
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Devuelve la etiqueta que muestra el estado de la ambulancia.
     *
     * @return ambulanceLbl
     */
    public JLabel getAmbulanceLbl() {
        return ambulanceLbl;
    }

    /**
     * Devuelve el botón encargado de solicitar mantenimiento.
     *
     * @return maintenanceBtn
     */
    public JButton getMaintenanceBtn() {
        return maintenanceBtn;
    }

    /**
     * Devuelve el botón de finalización de la simulación.
     *
     * @return stopBtn
     */
    public JButton getStopBtn() {
        return stopBtn;
    }
}
