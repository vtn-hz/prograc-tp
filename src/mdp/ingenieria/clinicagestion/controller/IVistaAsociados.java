package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IVistaAsociados extends IVista {
    void updateAsociados(List<AsociadoDTO> lista);
    String getIdFromRow(int selectedRow);
    void deleteAsociados();
    void addAsociadoRow(AsociadoDTO asociado);
    void removeAsociadoRow(int index);

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
