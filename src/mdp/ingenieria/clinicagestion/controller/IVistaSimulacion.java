package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;

public interface IVistaSimulacion extends IVista {
    /**
     * Agrega un asociado a la tabla de la simulación.
     *
     * @param nya   nombre y apellido del asociado
     * @param state estado inicial
     */
    void addAsociado(String nya, int state);
    /**
     * Actualiza el estado visual de un asociado.
     *
     * @param i     índice del asociado
     * @param state nuevo estado
     */
    void changeAsociadoState(int i, int state);
    /**
     * Registra una operación realizada durante la simulación.
     *
     * @param msg mensaje descriptivo
     */
    void addOperation(String msg);
    /**
     * Actualiza el estado visual de la ambulancia.
     *
     * @param state nuevo estado
     */
    void changeAmbulanceState(String state);
    /**
     * Limpia la vista para iniciar una nueva simulación.
     */
    void clearSimulation();
    /**
     * Muestra un mensaje temporal emergente.
     *
     * @param msg mensaje a mostrar
     * @param ms  duración en milisegundos
     */
    void popupMessage(String msg, int ms);
    /**
     * @return comando asociado al botón de detener simulación
     */
    String getStopSimulatioActionCommand();

    JPanel getMainPanel();
    JButton getMaintenanceBtn();
    JButton getStopBtn();
}
