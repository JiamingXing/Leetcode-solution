package design;

public class TicTacToe {
    char[] marks = {'1', 'O', 'X'};
    char[][] grid;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 'E';
            }
        }
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        grid[row][col] = marks[player];
        int len = grid.length;
        int count = 0;
        //check diognal
        if (row == col) {
            for (int i = 0; i < len; i++) {
                if (grid[i][i] != marks[player]) {
                    break;
                }
                count ++;
            }
            if (count == len) {
                return player;
            }
        }
        count = 0;
        if (row+col == len-1) {
            for (int i = 0; i < len; i++) {
                if (grid[i][len-1-i] != marks[player]) {
                    break;
                }
                count ++;
            }
            if (count == len) {
                return player;
            }
        }
        //check row
        count = 0;
        for (int i = 0; i < len; i++) {
            if (grid[row][i] != marks[player]) {
                break;
            }
            count ++;
        }
        if (count == len) {
            return player;
        }
        //check column
        count = 0;
        for (int i = 0; i < len; i++) {
            if (grid[i][col] != marks[player]) {
                break;
            }
            count ++;
        }
        if (count == len) {
            return player;
        }
        return 0;
    }
}
