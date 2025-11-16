package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IVistaAsociados extends IVista {
    /**
     * Actualiza la tabla con la lista de asociados.
     *
     * @param lista lista de asociados a mostrar
     */
    void updateAsociados(List<AsociadoDTO> lista);
    /**
     * Obtiene el identificador del asociado en la fila indicada.
     *
     * @param selectedRow índice de la fila seleccionada
     * @return id del asociado correspondiente
     */
    String getIdFromRow(int selectedRow);
    /**
     * Elimina todos los asociados mostrados en la vista.
     */
    void deleteAsociados();
    /**
     * Agrega una fila para el asociado indicado.
     *
     * @param asociado asociado a agregar
     */
    void addAsociadoRow(AsociadoDTO asociado);
    /**
     * Elimina la fila en el índice indicado.
     *
     * @param index índice de la fila a eliminar
     */
    void removeAsociadoRow(int index);
    /**
     * Muestra un mensaje de error.
     *
     * @param msg texto del error
     */
    void popupError(String msg);
    /**
     * Pregunta al usuario si desea eliminar todos los asociados.
     *
     * @return true si confirma la eliminación, false en caso contrario
     */
    boolean confirmDeleteAll();
    /**
     * Habilita o deshabilita los botones de la vista.
     *
     * @param enabled true para habilitar, false para deshabilitar
     */
    void enableButtons(boolean enabled);

    JPanel getMainPanel();
    JTable getTable();
    JButton getAddBtn();
    JButton getRemoveBtn();
    JButton getGenerateBtn();
    JButton getRemoveTableBtn();
    JButton getGenerateTableBtn();
    DefaultTableModel getModel();

    List<JTextField> getAllTextFields();
}
