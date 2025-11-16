package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import java.awt.*;

public interface IVistaConfiguracion extends IVista {
    /**
     * @return cantidad de asociados configurada por el usuario
     */
    int getNumAsoc();
    /**
     * @return cantidad de solicitudes por asociado
     */
    int getNumReq();
    /**
     * Muestra un mensaje de error.
     *
     * @param msg texto del error
     */
    void popupError(String msg);
    /**
     * @return true si los asociados deben generarse automáticamente
     */
    boolean getIsGenerated();

    JPanel getMainPanel();
    JTextField getTextField1();
    JTextField getTextField2();
    JButton getStartBtn();
}
