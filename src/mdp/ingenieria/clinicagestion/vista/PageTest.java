package mdp.ingenieria.clinicagestion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PageTest {
    private JPanel panel1;
    private JButton NextButton;
    private JButton BackButton;
    private JLabel Title;
    private JTable tablaAsoc;
    private JFrame frame;

    private int pageNum;
    private ArrayList<String> users;

    Color priColBg = new Color(246, 84, 63);
    Color priColFg = Color.WHITE;
    Color priColHv = new Color(210, 110, 100);
    Color priColPr = new Color(220, 140, 130);

    public PageTest() {
        initializeUI();
        initializeData();
        configureTable();
        frame.setVisible(true);

        pageNum = 1;
        NextButton.addActionListener(e -> {
            Title.setText("Page " + ++pageNum);
        });
        BackButton.addActionListener(e -> {
            Title.setText("Page " + --pageNum);
        });
    }

    private void initializeUI() {
        frame = new JFrame("Page 1");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // frame.setUndecorated(true);

        // setButtonsUI();
    }

    private void initializeData() {
        users = new ArrayList<>();
        users.add("Asociado 1");
        users.add("Asociado 2");
    }

    private void configureTable() {
        String[] columnNames = {"Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (String user : users) {
            model.addRow(new Object[]{user});
        }

        tablaAsoc.setModel(model);

        // setTableUI();
    }

    private void setButtonsUI() {
        styleButton(NextButton, priColBg, priColFg, priColHv, priColPr);
        styleButton(BackButton, priColBg, priColFg, priColHv, priColPr);
    }

    private void setTableUI() {
        tablaAsoc.setForeground(priColBg);
        tablaAsoc.setGridColor(priColBg);
        // tablaAsoc.setShowGrid(false);

        JTableHeader header = tablaAsoc.getTableHeader();
        header.setBackground(priColBg);
        header.setForeground(priColFg);

        header.setFont(tablaAsoc.getFont());
        header.setPreferredSize(
                new Dimension(
                        header.getWidth(),
                        tablaAsoc.getRowHeight() + tablaAsoc.getIntercellSpacing().height
                )
        );
        header.setBorder(BorderFactory.createEmptyBorder());

        tablaAsoc.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) c.setBackground(priColFg);
                return c;
            }
        });
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, Color hoverColor, Color pressedColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.contains(e.getPoint()))
                    button.setBackground(hoverColor);
                else
                    button.setBackground(bgColor);
            }
        });
    }

    public static void main(String[] args) {
        PageTest vista = new PageTest();
    }
}
