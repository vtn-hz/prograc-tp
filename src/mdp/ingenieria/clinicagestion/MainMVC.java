package mdp.ingenieria.clinicagestion;

import mdp.ingenieria.clinicagestion.controlador.ControladorAsociados;
import mdp.ingenieria.clinicagestion.controlador.ControladorNavegacion;
import mdp.ingenieria.clinicagestion.vista.VistaAsociados;
import mdp.ingenieria.clinicagestion.vista.VistaBase;

public class MainMVC {
    public static void main(String[] args) {
        ControladorNavegacion controladorNavegacion = new ControladorNavegacion();
        ControladorAsociados controladorAsociados = new ControladorAsociados();

        VistaBase vista = new VistaBase();
        controladorNavegacion.setVista(vista);
        controladorAsociados.setVista(vista.getVista1() );
    }
}
