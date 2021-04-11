package backtracking;


/*
    LeetCode 79. Word Search
    The same letter cell may not be used more than once
    DFS
 */

public class WordSearch {
    private static final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, boolean[][] visited, int i, int j, int pos) {
        if (pos == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(pos)) {
            return false;
        }
        visited[i][j] = true;
        boolean res = false;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            res = res || helper(board, word, visited, row, col, pos+1);
        }
        visited[i][j] = false;
        return res;
    }
}



//hard code 四个方向
/*
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, boolean[][] visited, String word, int i, int j, int pos) {
        if (pos == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(pos)) {
            return false;
        }
        visited[i][j] = true;
        boolean res = helper(board, visited, word, i+1, j, pos+1) || helper(board, visited, word, i-1, j, pos+1)
                || helper(board, visited, word, i, j+1, pos+1) || helper(board, visited, word, i, j-1, pos+1);
        visited[i][j] = false;
        return res;
    }
}
*/



//这个是最快的解法
//不用boolean[][] visited 而是暂时的改变board里的值
/*
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, int i, int j, int pos) {
        if (pos == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || board[i][j] != word.charAt(pos) || board[i][j] == '#') {
            return false;
        }
        char cur = board[i][j];
        board[i][j] = '#';
        boolean res = helper(board, word, i+1, j, pos+1) || helper(board, word, i-1, j, pos+1)
                || helper(board, word, i, j+1, pos+1) || helper(board, word, i, j-1, pos+1);
        board[i][j] = cur;
        return res;
    }
}
*/
