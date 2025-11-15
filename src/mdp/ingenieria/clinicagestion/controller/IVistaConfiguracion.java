package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import java.awt.*;

public interface IVistaConfiguracion extends IVista {
    int getNumAsoc();
    int getNumReq();
    void popupError(String msg);
    boolean getIsGenerated();

    JPanel getMainPanel();
    JTextField getTextField1();
    JTextField getTextField2();
    JButton getStartBtn();
}
