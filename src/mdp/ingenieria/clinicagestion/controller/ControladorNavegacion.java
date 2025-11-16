package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.view.VistaBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNavegacion extends Controlador {
    private IVistaBase vista;

    /**
     * Asocia una vista base a este controlador y registra este controlador
     * como su ActionListener.
     *
     * <b>pre:</b> vista no debe ser nula y debe implementar IVistaBase <br>
     * <b>post:</b> la vista queda asociada y preparada para enviar eventos
     * de navegación al controlador
     *
     * @param vista vista genérica que se convierte a IVistaBase
     */
    public void setVista(IVista vista) {
        this.vista = (IVistaBase) vista;
        vista.setActionListener(this);
    }

    /**
     * Procesa un evento de acción proveniente de la vista y ejecuta un cambio
     * de página dentro de la interfaz.
     *
     * <b>pre:</b> el ActionEvent debe contener un comando válido que represente
     * una página o sección de la vista <br>
     * <b>post:</b> se invoca showPage con el código recibido para actualizar
     * la interfaz mostrada al usuario
     *
     * @param e evento de acción generado por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String code = e.getActionCommand();
        this.vista.showPage(code);
    }
}
