package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryView extends JFrame {
    private JPanel panel;
    private JButton backButton;
    private JTable table;
    private Object[][] data;

    public HistoryView(Object[][] data) throws HeadlessException {
        this.data = data;
        setSize(500, 300);
        setTitle("User Record History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());
        table = new JTable();
        backButton = new JButton("Back");
        backButton.setBackground(new Color(235, 152, 78  ));

        String[] columnNames = {"Nickname", "Figure", "Date", "Result"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        table.setModel(model);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(235, 152, 78  ));

        // Agregar el panel de desplazamiento al marco
        panel.add(scrollPane);
        panel.add(backButton, BorderLayout.NORTH);
        add(panel);
    }

    public JButton getBackButton() {
        return backButton;
    }
}

