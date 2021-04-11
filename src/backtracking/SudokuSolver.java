package backtracking;


/*
    LeetCode 37. Sudoku Solver
    思路会很直接 就是挨个确认1-9的所有可能
    additional question : What is the time complexity of this solution?
 */

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        helper(board);
    }
    private boolean helper(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c < '9'; c ++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (helper(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c) {
                return false;
            }
            if (board[k][j] == c) {
                return false;
            }
            //怎么找这个对应的规律 首先我们要发现找对应的行值和行i有关 对应的列只和列j有关
            int row = k/3 + (i/3) * 3;
            int col = k%3 + (j/3) * 3;
            if (board[row][col] == c) {
                return false;
            }
        }
        return true;
    }
}
