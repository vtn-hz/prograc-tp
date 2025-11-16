package mdp.ingenieria.clinicagestion.view;

import mdp.ingenieria.clinicagestion.controller.IVista;
import mdp.ingenieria.clinicagestion.controller.IVistaSimulacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaSimulacion implements IVistaSimulacion {
    private JPanel mainPanel;
    private JButton stopBtn;
    private JPanel panel1;
    private JPanel panel2;
    private JButton maintenanceBtn;
    private JLabel ambulanceLbl;
    
    String[] states = {
    		"Esperando",
            "Utilizando",
            "Terminó"
    };
    
    Color[] colors = {
    	    new Color(220, 220, 220),  
    	    new Color(255, 229, 143),  
    	    new Color(180, 246, 100)   
    	};

    /**
     * Construye la vista de simulación y configura el layout
     * de los paneles internos y la etiqueta de ambulancia.
     *
     * <b>post:</b> panel1 y panel2 usan BoxLayout, y la etiqueta de ambulancia
     * tiene borde compuesto aplicado
     */
    public VistaSimulacion() {
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        ambulanceLbl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(122, 138, 153), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    /**
     * Registra el ActionListener que se utilizará para los botones
     * de mantenimiento y finalizar.
     * En el caso del botón de finalizar, se deshabilita y cambia el texto
     * antes de delegar al listener.
     *
     * <b>pre:</b> actionListener no debe ser null <br>
     * <b>post:</b> el listener queda asociado a maintenanceBtn y stopBtn
     *
     * @param actionListener manejador de acciones para esta vista
     */
    public void setActionListener(ActionListener actionListener) {
        maintenanceBtn.addActionListener(actionListener);

        stopBtn.addActionListener(e -> {
            stopBtn.setEnabled(false);
            stopBtn.setText("Finalizando");

            actionListener.actionPerformed(e);
        });
    }

    /**
     * Agrega visualmente un asociado a la lista de la simulación,
     * con un estado inicial indicado.
     *
     * <b>pre:</b> state es un índice válido del arreglo states <br>
     * <b>post:</b> se agrega una nueva fila al panel de asociados
     *
     * @param nya   nombre y apellido del asociado
     * @param state índice del estado inicial
     */
    public void addAsociado(String nya, int state) {
        FilaAsociado row = new FilaAsociado(nya, states[state]);
        panel1.add(row);
    }

    /**
     * Cambia el estado visual de un asociado ya mostrado.
     *
     * <b>pre:</b> i es un índice válido de componentes del panel de asociados,
     *             state es un índice válido de states/colors <br>
     * <b>post:</b> la fila se actualiza con nuevo texto y color de fondo
     *
     * @param i     índice del asociado en el panel
     * @param state nuevo estado (índice en states/colors)
     */
    public void changeAsociadoState(int i, int state) {
        FilaAsociado row = (FilaAsociado) panel1.getComponent(i);
        row.updateState(states[state], colors[state]);
        row.setBackground(colors[state]);
    }

    /**
     * Agrega una línea de texto al panel de operaciones,
     * simulando un log del flujo de la ambulancia y los asociados.
     *
     * @param msg mensaje a mostrar
     */
    public void addOperation(String msg) {
        JLabel label = new JLabel(msg);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel2.add(label);
    }

    /**
     * Limpia la simulación visual: borra asociados, operaciones,
     * y reinicia el estado del botón de finalizar.
     *
     * <b>post:</b> panel1 y panel2 quedan sin componentes, y stopBtn vuelve
     *              a estar habilitado con texto "Finalizar"
     */
    public void clearSimulation() {
        stopBtn.setText("Finalizar");
        stopBtn.setEnabled(true);
    	
    	panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();

        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
    }

    /**
     * Muestra un mensaje emergente por un tiempo determinado
     * y luego cierra automáticamente el diálogo.
     *
     * <b>pre:</b> ms >= 0 <br>
     * <b>post:</b> el diálogo se cierra automáticamente después del tiempo indicado
     *
     * @param msg mensaje a mostrar
     * @param ms  duración en milisegundos antes de cerrar el diálogo
     */
    public void popupMessage(String msg, int ms) {
        JDialog dialog = new JOptionPane(msg).createDialog("Mensaje");
        new javax.swing.Timer(ms, e -> dialog.dispose()).start();
        dialog.setVisible(true);
    }

    /**
     * Actualiza el texto que representa el estado de la ambulancia.
     *
     * @param state nuevo estado textual de la ambulancia
     */
    public void changeAmbulanceState(String state) {
        ambulanceLbl.setText(state);
    }

    /**
     * @return panel principal que contiene la vista de simulación
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @return botón que dispara la solicitud de mantenimiento
     */
    public JButton getMaintenanceBtn() {
        return maintenanceBtn;
    }

    /**
     * @return botón de finalización
     */
    public JButton getStopBtn() {
        return stopBtn;
    }

    /**
     * Devuelve el action command utilizado al detener la simulación,
     * para que el controlador pueda navegar a la página adecuada.
     *
     * @return cadena con el action command de retorno, típicamente PAGE2
     */
    public String getStopSimulatioActionCommand() {
		return "PAGE2";
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setName("Page3");
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 30;
        gbc.ipady = 10;
        mainPanel.add(panel3, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Page 3");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(label1, gbc);
        maintenanceBtn = new JButton();
        maintenanceBtn.setText("Solicitar mantenimiento");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel3.add(maintenanceBtn, gbc);
        stopBtn = new JButton();
        stopBtn.setActionCommand("PAGE2");
        stopBtn.setText("Finalizar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(stopBtn, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane1, gbc);
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-1));
        scrollPane1.setViewportView(panel1);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane2, gbc);
        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(new Color(-1));
        scrollPane2.setViewportView(panel2);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(panel4, gbc);
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setText("Estado ambulancia");
        panel4.add(label2, BorderLayout.NORTH);
        ambulanceLbl = new JLabel();
        ambulanceLbl.setBackground(new Color(-1));
        ambulanceLbl.setHorizontalAlignment(0);
        ambulanceLbl.setMaximumSize(new Dimension(33, 34));
        ambulanceLbl.setOpaque(true);
        ambulanceLbl.setPreferredSize(new Dimension(33, 34));
        ambulanceLbl.setText("...");
        panel4.add(ambulanceLbl, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
