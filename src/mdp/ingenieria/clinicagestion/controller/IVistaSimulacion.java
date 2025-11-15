package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;

public interface IVistaSimulacion extends IVista {
    void addAsociado(String nya, int state);
    void changeAsociadoState(int i, int state);
    void addOperation(String msg);
    void changeAmbulanceState(String state);
    void popupMessage(String msg, int ms);
    JPanel getMainPanel();
    JButton getMaintenanceBtn();
    JButton getStopBtn();
}
