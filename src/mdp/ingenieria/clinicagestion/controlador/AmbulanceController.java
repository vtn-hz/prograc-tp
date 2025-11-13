package mdp.ingenieria.clinicagestion.controlador;

import java.util.Observable;
import java.util.Observer;

public class AmbulanceController implements Observer {
    private Observable ambulancia;
    private IVista vista;

    public AmbulanceController(Observable ambulancia){
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // call vista.updateVista(arg.getstatus,  arg.getsolicitante.id)
    }
}
