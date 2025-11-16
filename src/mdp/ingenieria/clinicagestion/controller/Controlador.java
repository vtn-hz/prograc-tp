package mdp.ingenieria.clinicagestion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private IVista vista;
    private Object modelo;

    public Controlador(){
    }

    /**
     * Crea un controlador con un modelo y una vista asociados.
     * Registra este controlador como ActionListener de la vista.
     *
     * <b>pre:</b> modelo y vista no deben ser nulos; vista debe permitir registrar
     * un ActionListener <br>
     * <b>post:</b> se asocian modelo y vista al controlador y se registra como
     * ActionListener de la vista
     *
     * @param modelo objeto que representa la lógica o datos asociados
     * @param vista  interfaz gráfica desde la que se reciben eventos
     */
    public Controlador(Object modelo, IVista vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    /**
     * Metodo llamado ante un evento de acción.
     * Este metodo se sobrescribe en cada controlador hijo para implementar
     * la lógica específica requerida.
     *
     * <b>pre:</b> debe ser invocado por un componente gráfico registrado en la vista <br>
     * <b>post:</b> no modifica el estado del controlador base; la implementación concreta
     * se define en las clases hijas
     *
     * @param e evento de acción recibido desde la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Se implementa en cada hijo de este controlador
    }

    /**
     * @return vista vinculada al controlador
     */
    public IVista getVista(){
        return this.vista;
    }

    /**
     * Asigna una vista al controlador y lo registra como su ActionListener.
     *
     * <b>pre:</b> vista no debe ser nula <br>
     * <b>post:</b> el controlador queda asociado a la vista y registrado como
     * ActionListener para manejar sus eventos
     *
     * @param vista nueva vista a asociar al controlador
     */
    public void setVista(IVista vista){
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    /**
     * @return modelo asociado
     */
    public Object getModelo() {
        return this.modelo;
    }
}
