package view;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InitialView extends JFrame {

    private JButton playButton;
    private JButton historyButton;
    private JPanel panel;
    private JLabel label;
    private ImageIcon imageIcon;

    public InitialView(){
        setSize(300, 300);
        setTitle("Tik Tak Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initialComponents();

    }
    public void initialComponents() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panel.setBackground(new Color(248, 196, 113));

        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);

        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(200, 30));
        playButton.setBackground(new Color(235, 152, 78  ));
        playButton.setBorder(roundedBorder);

        historyButton = new JButton("Show history");
        historyButton.setPreferredSize(new Dimension(200, 30));
        historyButton.setBackground(new Color(235, 152, 78  ));
        historyButton.setBorder(roundedBorder);

        imageIcon = new ImageIcon("src/resources/img2.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        panel.add(imageLabel);

        label = new JLabel("Welcome to Tik Tak Toe");

        panel.add(label);
        panel.add(playButton);
        panel.add(historyButton);
        add(panel);
    }



    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getHistoryButton() {return historyButton;}

}
