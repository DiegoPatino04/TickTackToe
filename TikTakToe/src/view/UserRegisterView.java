package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserRegisterView extends JFrame {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private ImageIcon imageIcon;


    public UserRegisterView() {
        setSize(300, 300);
        setTitle("Tik Tak Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponent();
        setVisible(true);
        setResizable(false);
    }

    public void initializeComponent() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 196, 113));
        Border margin = new EmptyBorder(10, 10, 10, 10);
        GridBagConstraints constraints = new GridBagConstraints();

        imageIcon = new ImageIcon("src/resources/img1.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.gridwidth = 2;
        imageLabel.setBorder(margin);

        panel.add(imageLabel, constraints);

        label1 = new JLabel("Nickname");
        label1.setBorder(margin);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 50;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        panel.add(label1, constraints);

        textField = new JTextField();
        textField.setBorder(margin);
        constraints.ipadx = 100;
        constraints.ipady = 10;
        constraints.gridx = 1;
        constraints.gridy = 1;


        panel.add(textField, constraints);

        label2 = new JLabel("Choose a figure");
        label2.setBorder(margin);
        constraints.ipadx = 50;
        constraints.ipady = 10;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(label2, constraints);

        comboBox = new JComboBox<>();
        comboBox.setBorder(margin);
        comboBox.setBackground(new Color(248, 196, 113));
        comboBox.addItem("Circle");
        comboBox.addItem("Cross");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(comboBox, constraints);

        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);

        button1 = new JButton("Back");
        button1.setBorder(margin);
        button1.setBackground(new Color(235, 152, 78  ));
        button1.setBorder(roundedBorder);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(button1, constraints);

        button2 = new JButton("Continue");
        button2.setBorder(margin);
        button2.setBackground(new Color(235, 152, 78  ));
        button2.setBorder(roundedBorder);

        constraints.ipadx = 30;
        constraints.ipady = 10;
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(button2, constraints);

        add(panel);
    }


    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JButton getBackButton(){
        return button1;
    }


}