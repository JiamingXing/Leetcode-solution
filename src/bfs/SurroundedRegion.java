package bfs;

import java.util.LinkedList;
import java.util.Queue;

//这道题BFS的思路没问题 但是会超时
//DFS和BFS的思路都比较直接但是注意一些细节写法不同会导致stack overflow或超时的情况

public class SurroundedRegion {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '#';
                Q.offer(new int[]{i,0});
            }
            if (board[i][n-1] == 'O') {
                board[i][n-1] = '#';
                Q.offer(new int[]{i,n-1});
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = '#';
                Q.offer(new int[]{0,i});
            }
            if (board[m-1][i] == 'O') {
                board[m-1][i] = '#';
                Q.offer(new int[]{m-1,i});
            }
        }
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            for (int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                if (row < 0 || col < 0 || row >= m || col >= n) {
                    continue;
                }
                if (board[row][col] != 'O') {
                    continue;
                }
                Q.offer(new int[]{row, col});
                board[row][col] = '#';
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}





//DFS
/*
class Solution {
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
*/