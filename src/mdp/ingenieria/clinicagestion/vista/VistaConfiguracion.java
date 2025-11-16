package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VistaConfiguracion {
    private JPanel mainPanel;
    private JButton startBtn;
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;

    /**
     * Asigna el ActionListener a los componentes interactivos de la vista.
     *
     * <b>pre:</b> actionListener no debe ser null <br>
     * <b>post:</b> startBtn, textField1 y textField2 quedan asociados al listener
     *
     * @param actionListener listener que gestionará los eventos
     */
    public void setActionListener(ActionListener actionListener) {
        startBtn.addActionListener(actionListener);
        textField1.addActionListener(actionListener);
        textField2.addActionListener(actionListener);
    }

    /**
     * Devuelve el panel principal de esta vista.
     *
     * @return panel raíz de la vista
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Obtiene el campo numérico principal.
     *
     * @return textField1
     */
    public JTextField getTextField1() {
        return textField1;
    }

    /**
     * Obtiene el segundo campo numérico.
     *
     * @return textField2
     */
    public JTextField getTextField2() {
        return textField2;
    }

    /**
     * Devuelve el botón utilizado para iniciar la simulación.
     *
     * @return startBtn
     */
    public JButton getStartBtn() {
        return startBtn;
    }
}
