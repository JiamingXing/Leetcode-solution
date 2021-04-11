package com.jimmy.dfs;


//边缘的O不算被包围 正常的思路可能是遍历数组的所有可能 如果碰到O开始做DFS/BFS但是这样就会有个问题 就是边缘的O很难处理
//因为被包围的O如果和边缘的O相连了那么他们都不会改变
//我们如果逆向思维思考的话 从边缘的O出发 考虑所有和边缘相连的O多不改变 改变剩下的O 这道题就会简单很多
public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                helper(board, i, 0, visited);
            }
            if (board[i][n-1] == 'O') {
                helper(board, i, n-1, visited);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                helper(board, 0, i, visited);
            }
            if (board[m-1][i] == 'O') {
                helper(board, m-1, i, visited);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void helper(char[][] board, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        if (i+1 < board.length - 1 && board[i+1][j] == 'O' && !visited[i+1][j]) {
            helper(board, i+1, j, visited);
        }
        if (i-1 >= 1 && board[i-1][j] == 'O' && !visited[i-1][j]) {
            helper(board, i-1, j, visited);
        }
        if (j+1 < board[0].length -1 && board[i][j+1] == 'O' && !visited[i][j+1]) {
            helper(board, i, j+1, visited);
        }
        if (j-1 >= 1 && board[i][j-1] == 'O' && !visited[i][j-1]) {
            helper(board, i, j-1, visited);
        }
    }
}
