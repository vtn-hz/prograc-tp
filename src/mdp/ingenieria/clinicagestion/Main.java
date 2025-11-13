package mdp.ingenieria.clinicagestion;

import mdp.ingenieria.clinicagestion.controller.*;
import mdp.ingenieria.clinicagestion.view.VistaBase;

public class Main {
    public static void main(String[] args) {
        Controlador controladorNavegacion = new ControladorNavegacion();
        Controlador controladorAsociados = new ControladorAsociados();
        ControladorSimulacion controladorSimulacion = new ControladorSimulacion();

        VistaBase vista = new VistaBase();
        controladorNavegacion.setVista(vista);
        controladorAsociados.setVista(vista.getVista1());
        controladorSimulacion.setVistaConfig(vista.getVista2());
        controladorSimulacion.setVistaSim(vista.getVista3());
        controladorSimulacion.setVistaNav(vista);
    }
}
