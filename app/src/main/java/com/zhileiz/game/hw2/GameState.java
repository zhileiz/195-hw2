package com.zhileiz.game.hw2;

/**
 * Created by zhileizheng on 2/25/18.
 */

public class GameState {

    int[][] grids;

    public GameState() {
        grids = new int[4][4];
    }

    public void setGrid(int position, int userId) {
        int row = position / 4;
        int col = position % 4;
        if (userId % 2 == 0) {
            grids[row][col] = 7;
        } else {
            grids[row][col] = 13;
        }
    }

    public int getWinner() {
        int rowResult = checkRow();
        int colResult = checkCol();
        int diagResult = checkDiag();
        int antiDiagResult = checkAntiDiag();
        if (rowResult != 0) {
            return rowResult;
        } else if (colResult != 0) {
            return colResult;
        } else if (diagResult != 0) {
            return diagResult;
        } else if (antiDiagResult != 0) {
            return antiDiagResult;
        } else if (checkDraw()) {
            return 3;
        }
        return 0;
    }

    private int checkRow() {
        for (int row = 0; row < 4; row ++) {
            int num1 = grids[row][0];
            int num2 = grids[row][1];
            int num3 = grids[row][2];
            int num4 = grids[row][3];
            int winner = getWinner(num1, num2, num3, num4);
            if (winner > 0) {
                return winner;
            }
        }
        return 0;
    }

    private int checkCol() {
        for (int col = 0; col < 4; col ++) {
            int num1 = grids[0][col];
            int num2 = grids[1][col];
            int num3 = grids[2][col];
            int num4 = grids[3][col];
            int winner = getWinner(num1, num2, num3, num4);
            if (winner > 0) {
                return winner;
            }
        }
        return 0;
    }

    private int checkDiag() {
        int num1 = grids[0][0];
        int num2 = grids[1][1];
        int num3 = grids[2][2];
        int num4 = grids[3][3];
        int winner = getWinner(num1, num2, num3, num4);
        if (winner > 0) {
            return winner;
        }
        return 0;
    }

    private int checkAntiDiag() {
        int num1 = grids[0][3];
        int num2 = grids[1][2];
        int num3 = grids[2][1];
        int num4 = grids[3][0];
        int winner = getWinner(num1, num2, num3, num4);
        if (winner > 0) {
            return winner;
        }
        return 0;
    }

    private int getWinner(int num1, int num2, int num3, int num4) {
        if (determineWinner(num1, num2, num3, num4) == 1) {
            return 1;
        } else if (determineWinner(num1, num2, num3, num4) == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    private int determineWinner(int i, int j, int p, int q) {
        int sum = i + j + p + q;
        if (sum == 52) {
            return 1;
        } else if (sum == 28) {
            return 2;
        } else {
            return 0;
        }
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grids[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }



}
