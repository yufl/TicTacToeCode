package com.interview;

import java.util.Scanner;

public class TicTacToe {
    private int[][] data;

    public TicTacToe() {
        data = new int[3][3];
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("Start to play Tic-Tac-Toe.");
        System.out.println("The game only for two players, X and O.");
        System.out.println("Each player need to enter row number and column number with ',' contacting such as '1,2' and end with <enter> key.");
        System.out.println("And row or column number must be one value of 1, 2 or 3.");
        System.out.println("X player start to play:");
        Scanner stdin = new Scanner(System.in);
        boolean gameContinue = true;
        boolean isXPlayer = true;
        while (gameContinue) {
            String playerValue = stdin.nextLine();
            if (playerValue.indexOf(',') == -1) {
                System.out.println("Wrong input and reenter:");
                continue;
            }
            String[] values = playerValue.split(",");
            if (values.length != 2) {
                System.out.println("Wrong input and reenter:");
                continue;
            }
            try {
                int row = Integer.parseInt(values[0]);
                int column = Integer.parseInt(values[1]);
                if (row > 3 || row < 1) {
                    System.out.println("Wrong input and reenter:");
                    continue;
                }
                if (column > 3 || column < 1) {
                    System.out.println("Wrong input and reenter:");
                    continue;
                }
                try {
                    if (isXPlayer) {
                        ticTacToe.xPlace(row - 1, column - 1);
                    } else {
                        ticTacToe.oPlace(row - 1, column - 1);
                    }
                    ticTacToe.printSpace();
                    isXPlayer = !isXPlayer;
                    int result = ticTacToe.getGameResult();
                    switch (result) {
                        case 0:
                            System.out.println("Game is draw.");
                            ticTacToe.printSpace();
                            gameContinue = false;
                            break;
                        case 1:
                            System.out.println("X player wins");
                            ticTacToe.printSpace();
                            gameContinue = false;
                            break;
                        case 2:
                            System.out.println("O player wins");
                            ticTacToe.printSpace();
                            gameContinue = false;
                        default:
                            System.out.println(isXPlayer ? "X player enter:" : "O player enter:" );
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Wrong input and reenter:");
                    continue;
                } catch (IllegalStateException e) {
                    System.out.println(row + "," + column + " is already occupied and reenter:");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input and reenter:");
                continue;
            }
        }
    }

    /**
     * Print space: space means nothing is occupied the space,
     * 'X' means X is occupied the space while 'O' means O is occupied the space.
     * */
    public void printSpace() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String t = " ";
                switch (getSpaceValue(i, j)) {
                    case 1:
                        t = "X";
                        break;
                    case 2:
                        t = "O";
                        break;
                    default:
                        break;
                }
                System.out.print(t);
                if (j != 2) {
                    System.out.print("\t");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Judge if the space is already occupied.
     * @param i row index from 0 to 2.
     * @param j column index from 0 to 2.
     * @throws IllegalArgumentException i or j is out of range [0, 2]
     * */
    public boolean isSpaceOccupied(int i, int j) {
        int space = getSpaceValue(i, j);
        return space == 1 || space == 2;
    }

    /**
     * Occupied space at row i, column j for X.
     * @param i row index from 0 to 2.
     * @param j column index from 0 to 2.
     * @throws IllegalArgumentException i or j is out of range [0, 2]
     * @throws IllegalStateException space at row i, column j has already occupied by X or O.
     * */
    public void xPlace(int i, int j) {
        int space = getSpaceValue(i, j);
        if (space != 0) {
            throw new IllegalStateException("The space is already occupied by " + (space == 1 ? "X" : "O"));
        }
        setSpaceValue(i,j,1);
    }

    /**
     * Occupied space at row i, column j for O.
     * @param i row index from 0 to 2.
     * @param j column index from 0 to 2.
     * @throws IllegalArgumentException i or j is out of range [0, 2]
     * @throws IllegalStateException space at row i, column j has already occupied by x or o.
     * */
    public void oPlace(int i, int j) {
        int space = getSpaceValue(i, j);
        if (space != 0) {
            throw new IllegalStateException("The space is already occupied by " + (space == 1 ? "X" : "O"));
        }
        setSpaceValue(i, j, 2);
    }

    /**
     * Fetch game result.
     * @return return game result. 1 means x win, 2 means o win,
     * 0 means draw while -1 means game continue.
     * */
    public int getGameResult() {
        for (int i = 0; i < 3; i++) {
            switch (data[i][0]) {
                case 1:
                    if (data[i][1] == 1 && data[i][2] == 1) {
                        return 1;
                    }
                    break;
                case 2:
                    if (data[i][1] == 2 && data[i][2] == 2) {
                        return 2;
                    }
                    break;
                default:
                    break;
            }
            switch (data[0][i]) {
                case 1:
                    if (data[1][i] == 1 && data[2][i] == 1) {
                        return 1;
                    }
                    break;
                case 2:
                    if (data[1][i] == 2 && data[2][i] == 2) {
                        return 2;
                    }
                    break;
                default:
                    break;
            }
        }
        switch (data[0][0]) {
            case 1:
                if (data[1][1] == 1 && data[2][2] == 1) {
                    return 1;
                }
                break;
            case 2:
                if (data[1][1] == 2 && data[2][2] == 2) {
                    return 2;
                }
                break;
            default:
                break;
        }
        switch (data[0][2]) {
            case 1:
                if (data[1][1] == 1 && data[2][0] == 1) {
                    return 1;
                }
                break;
            case 2:
                if (data[1][1] == 2 && data[2][0] == 2) {
                    return 2;
                }
                break;
            default:
                break;
        }

        if (isGameFinish()) {
            return 0;
        }
        return -1;
    }

    private int getSpaceValue(int i, int j) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("Wrong row.");
        }
        if (j < 0 || j >= 3) {
            throw new IllegalArgumentException("Wrong column.");
        }
        return data[i][j];
    }

    private void setSpaceValue(int i, int j, int value) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("Wrong row.");
        }
        if (j < 0 || j >= 3) {
            throw new IllegalArgumentException("Wrong column.");
        }
        if (value > 2 || value < 0) {
            throw new IllegalArgumentException("Value only support 0, 1 and 2.");
        }
        data[i][j] = value;
    }

    private boolean isGameFinish() {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (data[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
