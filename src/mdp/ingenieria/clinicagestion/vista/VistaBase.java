package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VistaBase {
    private JPanel mainPanel;
    private JButton page1Btn;
    private JButton page2Btn;

    private JPanel contentPanel;

    private VistaAsociados vista1;
    private VistaConfiguracion vista2;
    private VistaSimulacion vista3;
    private CardLayout cardLayout;

    public VistaBase() {
        JFrame frame = new JFrame("Clinica GUI");

        setupFrame(frame);
        setupContent();

        frame.setVisible(true);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    private void setupFrame(JFrame frame) {
        frame.setContentPane(mainPanel); // agrega la base al JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack(); // configura el tamaño mínimo de la interfaz en base a sus componentes
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    private void setupContent() {
        vista1 = new VistaAsociados();
        vista2 = new VistaConfiguracion();
        vista3 = new VistaSimulacion();

        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout); // la parte dinámica de la página

        contentPanel.add(vista1.getMainPanel(), "PAGE1");
        contentPanel.add(vista2.getMainPanel(), "PAGE2");
        contentPanel.add(vista3.getMainPanel(), "PAGE3");

        showPage("PAGE1"); // muestra la primera página
    }

    public void showPage(String page) {
        cardLayout.show(contentPanel, page);

        if (Objects.equals(page, "PAGE3")) {
            page1Btn.setEnabled(false);
            page2Btn.setEnabled(false);
        } else {
            page1Btn.setEnabled(true);
            page2Btn.setEnabled(true);
        }
    }

    public void setActionListener(ActionListener actionListener) {
        page1Btn.addActionListener(actionListener);
        page2Btn.addActionListener(actionListener);

        vista3.getStopBtn().addActionListener(actionListener);
    }

    public VistaAsociados getVista1() {
        return vista1;
    }

    public VistaConfiguracion getVista2() {
        return vista2;
    }

    public VistaSimulacion getVista3() {
        return vista3;
    }
}
