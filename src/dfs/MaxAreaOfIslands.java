package dfs;

import java.util.LinkedList;
import java.util.Queue;


//BFS+visited 19.76% 更慢
public class MaxAreaOfIslands {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int curMax = 1;
                    Queue<int[]> Q = new LinkedList<>();
                    Q.offer(new int[]{i,j});
                    visited[i][j] = true;
                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();
                        int row = cur[0];
                        int col = cur[1];
                        if (row+1 < m && grid[row+1][col] == 1 && !visited[row+1][col]) {
                            Q.offer(new int[]{row+1,col});
                            curMax ++;
                            visited[row+1][col] = true;
                        }
                        if (row-1 >= 0 && grid[row-1][col] == 1 && !visited[row-1][col]) {
                            Q.offer(new int[]{row-1, col});
                            curMax ++;
                            visited[row-1][col] = true;
                        }
                        if (col+1 < n && grid[row][col+1] == 1 && !visited[row][col+1]) {
                            Q.offer(new int[]{row,col+1});
                            curMax ++;
                            visited[row][col+1] = true;
                        }
                        if (col-1 >= 0 && grid[row][col-1] == 1 && !visited[row][col-1]) {
                            Q.offer(new int[]{row, col-1});
                            curMax ++;
                            visited[row][col-1] = true;
                        }
                    }
                    res = Math.max(res, curMax);
                }
            }
        }
        return res;
    }
}


//二刷写的DFS又快有简洁...
/*
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = helper(grid, i, j);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }
    private int helper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        res += helper(grid, i+1, j);
        res += helper(grid, i-1, j);
        res += helper(grid, i, j+1);
        res += helper(grid, i, j-1);
        return res;
    }
}
 */




//DFS+visited 31% -> how to improve?
/*
public class MaxAreaOfIslands {
    private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, helper(grid, visited, i, j));
                }
            }
        }
        return res;
    }
    private int helper(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        int res = 1;
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            res += helper(grid, visited, row, col);
        }
        return res;
    }
}
*/




/*
public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, m, n, 0);
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }

    int dfs(int[][] grid, int i, int j, int m, int n, int area) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return area;
        }
        grid[i][j] = 0;
        area++;
        area = dfs(grid, i + 1, j, m, n, area);
        area = dfs(grid, i, j + 1, m, n, area);
        area = dfs(grid, i - 1, j, m, n, area);
        area = dfs(grid, i, j - 1, m, n, area);
        return area;
    }
 */