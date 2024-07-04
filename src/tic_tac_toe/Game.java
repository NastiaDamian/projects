package tic_tac_toe;

import java.util.Scanner;

public class Game {
    public static final char EMPTY = ' ';
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[3][3];
        initializeGrid(grid);

        char currentPlayer = PLAYER_X;
        boolean gameEnded = false;

        printGrid(grid);

        while (!gameEnded) {
            System.out.println("Enter cell coordinates for " + currentPlayer + " (format: row column): ");
            String move = scanner.nextLine();

            switch (validateMove(grid, move)) {
                case "valid":
                    updateGrid(grid, currentPlayer, move);
                    printGrid(grid);
                    if (checkWinner(grid, currentPlayer)) {
                        System.out.println(currentPlayer + " wins");
                        gameEnded = true;
                    } else if (isDraw(grid)) {
                        System.out.println("Draw");
                        gameEnded = true;
                    } else {
                        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                    }
                    break;
                case "occupied":
                    System.out.println("This cell is occupied! Choose another one!");
                    break;
                case "outOfRange":
                    System.out.println("Coordinates should be from 1 to 3!");
                    break;
                case "invalid":
                default:
                    System.out.println("You should enter numbers!");
                    break;
            }
        }

        scanner.close();
    }

    private static void initializeGrid(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }

    private static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String validateMove(char[][] grid, String move) {
        String[] parts = move.split(" ");
        if (parts.length != 2 || !isNumeric(parts[0]) || !isNumeric(parts[1])) {
            return "invalid";
        }

        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (row < 1 || row > 3 || col < 1 || col > 3) {
            return "outOfRange";
        }

        if (grid[row - 1][col - 1] != EMPTY) {
            return "occupied";
        }

        return "valid";
    }

    private static void updateGrid(char[][] grid, char player, String move) {
        String[] parts = move.split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        grid[row - 1][col - 1] = player;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkWinner(char[][] grid, char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }

        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }

        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }

        return false;
    }

    private static boolean isDraw(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

