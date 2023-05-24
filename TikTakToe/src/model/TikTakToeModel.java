package model;

import java.util.Random;

public class TikTakToeModel {
    private char[][] board;
    private char playerSymbol;
    private char computerSymbol;

    public TikTakToeModel(String playerSymbol) {
        board = new char[3][3];
        this.playerSymbol = playerSymbol.charAt(0);
        if (this.playerSymbol == 'X') {
            computerSymbol = 'O';
        } else {
            computerSymbol = 'X';
        }
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(char symbol) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public boolean hasPlayerWon() {
        return hasWon(playerSymbol);
    }

    public boolean hasComputerWon() {
        return hasWon(computerSymbol);
    }

    public void makeComputerMove() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') {
                    board[row][col] = computerSymbol;
                    if (hasComputerWon()) {
                        return;
                    }
                    board[row][col] = '\u0000';
                }
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') {
                    board[row][col] = playerSymbol;
                    if (hasPlayerWon()) {
                        board[row][col] = computerSymbol;
                        return;
                    }
                    board[row][col] = '\u0000';
                }
            }
        }
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != '\u0000');
        board[row][col] = computerSymbol;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char figure, int row, int column) {
        board[row][column] = figure;
    }

    public void resetBoard() {
        board = new char[3][3];
    }
}
