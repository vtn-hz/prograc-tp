package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import java.awt.*;

public interface IVistaConfiguracion extends IVista {
    JPanel getMainPanel();
    JTextField getTextField1();
    JTextField getTextField2();
    JButton getStartBtn();
}
