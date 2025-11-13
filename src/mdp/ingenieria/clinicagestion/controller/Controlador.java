package mdp.ingenieria.clinicagestion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private IVista vista;
    private Object modelo;

    public Controlador(){
    }

    public Controlador(Object modelo, IVista vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implementar en cada hijo de este controlador
    }

    public IVista getVista(){
        return this.vista;
    }

    public void setVista(IVista vista){
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    public Object getModelo() {
        return this.modelo;
    }
}
