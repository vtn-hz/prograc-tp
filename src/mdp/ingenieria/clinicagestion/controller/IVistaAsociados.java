package mdp.ingenieria.clinicagestion.controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IVistaAsociados extends IVista {
    void addAsociado(String name, String id, String address);
    void removeAsociado(int index);

    JPanel getMainPanel();
    JTable getTable();
    JButton getAddButton();
    JButton getRemoveButton();
    JButton getGenerateBtn();
    DefaultTableModel getModel();

    List<JTextField> getAllTextFields();
}
