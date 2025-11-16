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

    /**
     * Crea la ventana principal, configurando el frame y
     * cargando las distintas vistas en el panel de contenido.
     *
     * <b>post:</b> se muestra el frame con PAGE1 activa
     */
    public VistaBase() {
        JFrame frame = new JFrame("Clinica GUI");

        setupFrame(frame);
        setupContent();

        frame.setVisible(true);
    }

    /**
     * Devuelve el panel de contenido administrado por el CardLayout.
     *
     * @return panel donde se muestran las distintas páginas
     */
    public JPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Configura el frame principal con el panel base, el comportamiento de cierre
     * y el tamaño inicial de la ventana.
     *
     * <b>pre:</b> frame no debe ser null <br>
     * <b>post:</b> el frame queda asociado a mainPanel y centrado en pantalla
     *
     * @param frame ventana principal de la aplicación
     */
    private void setupFrame(JFrame frame) {
        frame.setContentPane(mainPanel); // agrega la base al JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack(); // configura el tamaño mínimo de la interfaz en base a sus componentes
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Crea las vistas internas, configura el CardLayout y registra cada vista
     * bajo un nombre de página.
     *
     * <b>post:</b> vista1, vista2 y vista3 quedan inicializadas y asociadas a
     *              PAGE1, PAGE2 y PAGE3 respectivamente; se muestra PAGE1
     */
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

    /**
     * Muestra la página indicada dentro del panel de contenido.
     * Si se trata de PAGE3, deshabilita los botones de navegación
     * para evitar cambiar de vista mientras se simula.
     *
     * <b>pre:</b> page debe coincidir con alguna de las páginas registradas (PAGE1, PAGE2, PAGE3) <br>
     * <b>post:</b> la vista solicitada queda visible
     *
     * @param page identificador de la página a mostrar
     */
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

    /**
     * Registra un ActionListener para los botones de navegación
     * y para el botón de stop de la simulación.
     *
     * <b>pre:</b> actionListener no debe ser null <br>
     * <b>post:</b> el listener queda asociado a page1Btn, page2Btn y stopBtn de vista3
     *
     * @param actionListener manejador de acciones global para la base de la vista
     */
    public void setActionListener(ActionListener actionListener) {
        page1Btn.addActionListener(actionListener);
        page2Btn.addActionListener(actionListener);

        vista3.getStopBtn().addActionListener(actionListener);
    }

    /**
     * Devuelve la vista de asociados (PAGE1).
     *
     * @return instancia de VistaAsociados
     */
    public VistaAsociados getVista1() {
        return vista1;
    }

    /**
     * Devuelve la vista de configuración (PAGE2).
     *
     * @return instancia de VistaConfiguracion
     */
    public VistaConfiguracion getVista2() {
        return vista2;
    }

    /**
     * Devuelve la vista de simulación (PAGE3).
     *
     * @return instancia de VistaSimulacion
     */
    public VistaSimulacion getVista3() {
        return vista3;
    }
}
