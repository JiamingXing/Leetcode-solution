package com.jimmy.dfs;

/*
    LeetCode 200. Number of Islands
 */

import java.util.LinkedList;
import java.util.Queue;

//BFS做法
//Memory limit exceeded
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                Queue<int[]> Q = new LinkedList<>();
                Q.add(new int[]{i, j});
                res ++;
                while (!Q.isEmpty()) {
                    int size = Q.size();
                    for (int k = 0; k < size; k++) {
                        int[] cur = Q.poll();
                        int row = cur[0];
                        int col = cur[1];
                        if (row + 1 < m && !visited[row+1][col] && grid[row+1][col] == '1') {
                            Q.add(new int[]{row+1, col});
                        }
                        if (row - 1 >= 0 && !visited[row-1][col] && grid[row-1][col] == '1') {
                            Q.add(new int[]{row-1, col});
                        }
                        if (col + 1 < n && !visited[row][col+1] && grid[row][col+1] == '1') {
                            Q.add(new int[]{row, col+1});
                        }
                        if (col -1 >= 0 && !visited[row][col-1] && grid[row][col-1] == '1') {
                            Q.add(new int[]{row, col-1});
                        }
                    }
                }
            }
        }
        return res;
    }
}



//DFS做法
//如果允许改变原来的数组 我们可以不用boolean数组记录 visited 访问过的1直接变成0就可以
/*
public class NumberOfIslands {
    private static final int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                helper(grid, visited, i, j);
                res ++;
            }
        }
        return res;
    }
    private void helper(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            helper(grid, visited, row, col);
        }
    }
}
*/