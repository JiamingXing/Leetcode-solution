package backtracking;

/*
    LeetCode 51 N-Queens
 */

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        helper(res, board, 0, n);
        return res;
    }
    private void helper(List<List<String>> res, char[][] board, int row, int n) {
        if (row == n) {
            res.add(transfer(board));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(board, row, i)) {
                board[row][i] = 'Q';
                helper(res, board, row+1, n);
                board[row][i] = '.';
            }
        }
    }
    private List<String> transfer(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        List<String> nQueens = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
            nQueens.add(sb.toString());
        }
        return nQueens;
    }
    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
            for (int j = 0; j < board[0].length; j++) {
                if ((row - i == col - j || row - i == j - col) && board[i][j] == 'Q') {
                    return false;
                }
            }
        }
        return true;
    }
}
