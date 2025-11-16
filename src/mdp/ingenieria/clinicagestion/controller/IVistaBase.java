package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import java.awt.*;

public interface IVistaBase extends IVista {
    /**
     * Muestra la página indicada por su código.
     *
     * @param page identificador de la página a mostrar
     */
    void showPage(String page);
    /**
     * @return primera vista contenida en la navegación
     */
    IVista getVista1();
    /**
     * @return segundaa vista contenida en la navegación
     */
    IVista getVista2();
    /**
     * @return tercera vista contenida en la navegación
     */
    IVista getVista3();
}
