// Presenter.java

package presenter;

import model.*;
import view.*;
import persistence.*;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Presenter {

    private TikTakToeModel tikTakToe;
    private InitialView initialView;
    private UserRegisterView userRegisterView;
    private HistoryView historyView;
    private History history;
    private GameView gameView;
    private String userFigure;
    private String nickname;

    public Presenter() {
        userFigure = "";
        history = new History();
    }

    public void showInitialView() {
        initialView = new InitialView();
        initialView.getPlayButton().addActionListener(e -> {
            initialView.setVisible(false);
            showUserRegisterView();
        });
        initialView.getHistoryButton().addActionListener(e -> {
            initialView.setVisible(false);
            showHistory();
        });
    }

    public void showHistory(){
        Object[][] data = getUserRecordData();
        historyView = new HistoryView(data);
        historyView.getBackButton().addActionListener(e -> {
            historyView.setVisible(false);
            showInitialView();
        });
    }
    public void showUserRegisterView() {
        userRegisterView = new UserRegisterView();
        userRegisterView.getBackButton().addActionListener(e -> {
            userRegisterView.setVisible(false);
            showInitialView();
        });

        userRegisterView.getButton2().addActionListener(e -> {
            boolean check = checkUserRegistration();

            if (check) {
                checkFigure();
                userRegisterView.setVisible(false);
                initializeTikTakToe();
            }
        });
    }
    public void checkFigure() {
        String selectedOption = (String) userRegisterView.getComboBox().getSelectedItem();

        if (selectedOption.equals("Circle")) {
            userFigure = "O";
        } else if (selectedOption.equals("Cross")) {
            userFigure = "X";
        }
    }

    public boolean checkUserName() {
        nickname = userRegisterView.getTextField().getText().trim();
        return !nickname.isEmpty();
    }

    public boolean checkUserRegistration() {
        return checkUserName();
    }

    public void showGameView() {
        gameView = new GameView(nickname, userFigure);
        gameView.getHomeButton().addActionListener(e -> {
            gameView .setVisible(false);
            history.saveRecords();
            showInitialView();
        });

        gameView.getUserInfoButton().addActionListener(e -> {
            gameView.showUserInformation();
        });

        gameView.getCreditsButton().addActionListener(e -> {
            gameView.showCredits();
        });
    }

    public void initializeTikTakToe() {
        tikTakToe = new TikTakToeModel(userFigure);
        showGameView();
        userRegisterView.setVisible(false);

        JButton[][] board = gameView.getButtons();

        for (int i = 0; i < 3; i++) {
            int row = i;
            for (int j = 0; j < 3; j++) {
                int column = j;
                board[i][j].addActionListener(e -> {
                    if (board[row][column].getText().isEmpty()) {
                        gameView.setButtons(userFigure, row, column);
                        tikTakToe.setBoard(userFigure.charAt(0), row, column);
                        playComputerMove();
                    }
                });
            }
        }
    }

    public void playComputerMove() {
        if (tikTakToe.isBoardFull() || tikTakToe.hasPlayerWon() || tikTakToe.hasComputerWon()) {
            showResult();
            return;
        }

        tikTakToe.makeComputerMove();
        updateBoard();

        if (tikTakToe.hasComputerWon() || tikTakToe.isBoardFull() || tikTakToe.hasPlayerWon()) {
            showResult();
        }
    }

    public void updateBoard() {
        char[][] boardData = tikTakToe.getBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardData[row][col] != '\u0000') {
                    String symbol = String.valueOf(boardData[row][col]);
                    gameView.setButtons(symbol, row, col);
                }
            }
        }
    }

    public void showResult() {
        UserRecord userRecord;
        LocalDateTime date = LocalDateTime.now();
        if (tikTakToe.hasComputerWon()) {
            gameView.showWhoWins(0);
            userRecord = new UserRecord(nickname, userFigure.charAt(0), date, "Defeat" );
            history.addUserRecord(userRecord);

        } else if (tikTakToe.hasPlayerWon()) {
            gameView.showWhoWins(2);
            userRecord = new UserRecord(nickname, userFigure.charAt(0), date, "Win" );
            history.addUserRecord(userRecord);

        } else {
            gameView.showWhoWins(1);
            userRecord = new UserRecord(nickname, userFigure.charAt(0), date, "Draw" );
            history.addUserRecord(userRecord);

        }
        restartGame();
    }

    public void restartGame() {
        int option = gameView.showReplayOption(gameView);
        if (option == JOptionPane.YES_OPTION) {
            tikTakToe.resetBoard();
            gameView.resetButtons();
            gameView.setVisible(false);
            history.saveRecords();
            history.resetUserRecords();
            showInitialView();
        } else {
            history.saveRecords();
            System.exit(0);
        }
    }


    private Object[][] getUserRecordData() {
        String filePath = "src/resources/history_game.txt";
        List<UserRecord> userRecords = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nickname = parts[0];
                char figure = parts[1].charAt(0);
                LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
                String result = parts[3];
                UserRecord userRecord = new UserRecord(nickname, figure, dateTime, result);
                userRecords.add(userRecord);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[userRecords.size()][4];
        for (int i = 0; i < userRecords.size(); i++) {
            UserRecord userRecord = userRecords.get(i);
            data[i][0] = userRecord.getUsername();
            data[i][1] = userRecord.getUserFigure();
            data[i][2] = userRecord.getDateTime();
            data[i][3] = userRecord.getResult();
        }
        int start = 0;
        int end = data.length - 1;

        while (start < end) {
            Object[] temp = data[start];
            data[start] = data[end];
            data[end] = temp;

            start++;
            end--;
        }
        return data;
    }

    public void run() {
        showInitialView();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Presenter().run();
            }
        });
    }
}

