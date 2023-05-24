package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private JPanel panel1;
    private JPanel panel2;
    private JButton [][] buttons;
    private JButton userInfoButton;
    private JButton creditsButton;
    private JButton homeButton;;
    private String userName;
    private String userFigure;
    private String userInformation;
    private String credits;


    public GameView(String userName, String userFigure){
        this.userName = userName;
        this.userFigure = userFigure;
        setSize(300, 330);
        setTitle("Tik Tak Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initialComponents();
    }

    private void initialComponents() {
        panel1 = new JPanel(new GridLayout(3, 3));
        panel2 = new JPanel();
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setBackground(new Color(248, 196, 113));
                buttons[i][j] = btn;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = buttons[i][j];
                btn.setBackground(new Color(248, 196, 113));
                panel1.add(btn);
            }
        }

        userInformation = "Nickname:     " + userName + " " + "\n" + "Figure:       " + userFigure;
        credits = "Luis Javier Lopez Galindo 202114366" + "\n" + "Diego Alejandro Patiño Vega 202021537" + "\n"
                + "Gustavo Andres Barrera Arcos 201920837" + "\n" + "\n" + "Universidad Pediagogica y Tecnologica de Colombia" +
                "\n" + "Facultad de Ingenieria" + "\n" + "Escuela de Ingenieria de Sistemas y Computacion" + "\n" + "2023-1";

        homeButton = new JButton("Home");
        userInfoButton = new JButton("UserInfo");
        creditsButton = new JButton("Credits");

        panel2.add(homeButton);
        panel2.add(userInfoButton);
        panel2.add(creditsButton);

        panel1.setPreferredSize(new Dimension(300, 300));
        panel2.setPreferredSize(new Dimension(300, 30));

        setLayout(new BorderLayout());

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
    }
    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(String figure, int row, int column) {
        JButton button = buttons[row][column];
        button.setText(figure);

    }

    public void resetButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public JButton getUserInfoButton() {
        return userInfoButton;
    }

    public JButton getCreditsButton() {
        return creditsButton;
    }

    public JButton getHomeButton(){
        return homeButton;
    }

    public void showUserInformation() {
        JOptionPane.showMessageDialog(null, userInformation, "User Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCredits() {
        JOptionPane.showMessageDialog(null, credits, "Credits", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showWhoWins(int result) {
        if (result == 0) {
            UIManager.put("OptionPane.background", Color.RED);
            JOptionPane.showMessageDialog(null, "Machine has won", "Defeat", JOptionPane.INFORMATION_MESSAGE);
        }
        if (result == 1) {
            UIManager.put("OptionPane.background", Color.GRAY);
            JOptionPane.showMessageDialog(null, "The game has ended in a draw", "Draw", JOptionPane.INFORMATION_MESSAGE);
        }
        if (result == 2) {
            UIManager.put("OptionPane.background", Color.GREEN);
            JOptionPane.showMessageDialog(null, "You win!!", "Win", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int showReplayOption(GameView gameView){
        int option = JOptionPane.showConfirmDialog(gameView, "¿do you want to play again?", "Restart game", JOptionPane.YES_NO_OPTION);
        return option;
    }

}
