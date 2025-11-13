package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import java.awt.*;

public interface IVistaBase extends IVista {
    void showPage(String page);
    IVista getVista1();
    IVista getVista2();
    IVista getVista3();
}
