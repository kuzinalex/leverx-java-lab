package com.kuzin;

import java.util.*;

class TicTacToe {
    private final char SIGN_X = 'X';
    private final char SIGN_O = 'O';
    private final char SIGN_EMPTY = ' ';
    private char[][] gameField;
    private String gameMode;
    private boolean isXTurn;
    private Random random;
    private Scanner scanner;


    TicTacToe() {
        random = new Random();
        scanner = new Scanner(System.in);
        gameField = new char[3][3];
        isXTurn = true;
    }

    private void playersVsAIGame() {
        initGameField();
        printGameField();
        while (true) {
            turnPlayer();
            if (checkWin(SIGN_X)) {
                System.out.println("YOU WIN!");
                break;
            }
            if (isGameFieldFull()) {
                System.out.println("Sorry, DEAD HEAT!");
                break;
            }
            turnAI();
            printGameField();
            if (checkWin(SIGN_O)) {
                System.out.println("AI WIN!");
                break;
            }
            if (isGameFieldFull()) {
                System.out.println("Sorry, DEAD HEAT!");
                break;
            }
        }
        System.out.println("GAME OVER.");
        printGameField();
    }

    private void twoPlayersGame() {
        initGameField();
        printGameField();
        while (true) {
            System.out.println("Player 1");
            turnPlayer();
            printGameField();
            if (checkWin(SIGN_X) || checkWin(SIGN_O)) {
                System.out.println("Player 1 WIN!");
                break;
            }
            if (isGameFieldFull()) {
                System.out.println("Sorry, DEAD HEAT!");
                break;
            }
            System.out.println("Player 2");
            turnPlayer();
            printGameField();
            if (checkWin(SIGN_O) || checkWin(SIGN_X)) {
                System.out.println("Player 2 WIN!");
                break;
            }
            if (isGameFieldFull()) {
                System.out.println("Sorry, DEAD HEAT!");
                break;
            }
        }
        System.out.println("GAME OVER.");
        printGameField();
    }

    public void play() {
        while (true) {
            System.out.print("1. Player VS AI\n2. Player VS Player\nChoose game mode:");
            gameMode = scanner.nextLine();
            if (gameMode.matches("[1-2]")) {
                switch (gameMode) {
                    case "1": {
                        playersVsAIGame();
                        break;
                    }
                    case "2": {
                        twoPlayersGame();
                        break;
                    }
                    default: {
                        System.out.println("Select 1 or 2");
                        break;
                    }
                }
            }
        }
    }

    private void initGameField() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                gameField[row][column] = SIGN_EMPTY;
            }
        }
    }

    private void printGameField() {
        for (int row = 0; row < 3; row++) {
            System.out.print(" | ");
            for (int column = 0; column < 3; column++) {
                System.out.print(gameField[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    private void turnPlayer() {
        int x = 4;
        int y = 4;
        String input;
        if (gameMode.equals("1") || isGameFieldEmpty()) {
            isXTurn = true;
        }

        do {
            System.out.println("Enter X and Y (1..3):");
            System.out.print("Row: ");
            input = scanner.nextLine();
            if (!input.matches("[1-3]")) {
                continue;
            } else {
                x = Integer.parseInt(input) - 1;
            }
            System.out.print("Column: ");
            input = scanner.nextLine();
            if (!input.matches("[1-3]")) {
                continue;
            } else {
                y = Integer.parseInt(input) - 1;
            }
        } while (!isCellValid(x, y));

        if (isXTurn) {
            gameField[x][y] = SIGN_X;
        } else {
            gameField[x][y] = SIGN_O;
        }
        isXTurn = !isXTurn;

    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            return false;
        }
        return gameField[x][y] == SIGN_EMPTY;
    }

    private void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        gameField[x][y] = SIGN_O;
    }

    private boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((gameField[i][0] == dot && gameField[i][1] == dot &&
                    gameField[i][2] == dot) ||
                    (gameField[0][i] == dot && gameField[1][i] == dot &&
                            gameField[2][i] == dot))
                return true;
        if ((gameField[0][0] == dot && gameField[1][1] == dot &&
                gameField[2][2] == dot) ||
                (gameField[2][0] == dot && gameField[1][1] == dot &&
                        gameField[0][2] == dot))
            return true;
        return false;
    }

    private boolean isGameFieldFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (gameField[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }

    private boolean isGameFieldEmpty() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (gameField[row][col] != SIGN_EMPTY)
                    return false;
        return true;
    }
}
