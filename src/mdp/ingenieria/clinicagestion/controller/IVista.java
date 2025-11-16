package mdp.ingenieria.clinicagestion.controller;

import java.awt.event.ActionListener;

public interface IVista {
    /**
     * Registra un ActionListener en la vista, permitiendo
     * que los componentes gráficos notifiquen eventos al controlador.
     *
     * <b>pre:</b> actionListener no debe ser nulo <br>
     * <b>post:</b> la vista queda asociada al ActionListener indicado,
     * y los eventos generados por la interfaz serán enviados a dicho listener
     *
     * @param actionListener objeto encargado de manejar los eventos de la vista
     */
    void setActionListener(ActionListener actionListener);
}
