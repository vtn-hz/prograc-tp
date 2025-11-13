package mdp.ingenieria.clinicagestion.controller;

import java.util.Observable;
import java.util.Observer;

public class ControladorAmbulancia extends Controlador implements Observer {
    private Observable ambulancia;
    private IVista vista;

    public ControladorAmbulancia(Observable ambulancia){
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // call vista.updateVista(arg.getstatus,  arg.getsolicitante.id)
    }
}
