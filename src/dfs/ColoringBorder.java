package dfs;

//比较典型的二维数组里面用DFS或者BFS遍历的问题
//这里加了个东西是找相连的component的border进行染色
//border的定义也很明显了 四个方向如果有grid的边界或者不同的颜色就是border
//分析一下我们要做什么事情？
//从起点出发通过DFS/BFS 找到边界
//对于四个相邻的点 如果相同颜色 表示我们需要往这个相邻的点进行DFS
//主要是标记

import java.util.HashSet;
import java.util.Set;

public class ColoringBorder {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        //given point, find connected component border
        int m = grid.length;
        int n = grid[0].length;
        Set<int[]> set = new HashSet<>();
        dfs(grid, r0, c0, set);
        for (int[] cor : set) {
            grid[cor[0]][cor[1]] = color;
        }
        return grid;
    }
    private void dfs(int[][] grid, int i, int j, Set<int[]> set) {
        int color = grid[i][j];
        boolean isBord = false;
        grid[i][j] = -1;
        for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != -1 && grid[row][col] != color) {
                isBord = true;
            }
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == color) {
                dfs(grid, row, col, set);
            }
        }
        if (isBord) {
            set.add(new int[]{i, j});
        }
        grid[i][j] = color;
    }
}


//这里不用set记录border 而是用-2标记记录border 标记为-1表示走过的点并且不是border 需要mark回原来的颜色
/*
class Solution {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        //given point, find connected component border
        int m = grid.length;
        int n = grid[0].length;
        dfs(grid, r0, c0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -2) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }
    private void dfs(int[][] grid, int i, int j) {
        int color = grid[i][j];
        grid[i][j] = -1;
        for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] >= 0 && grid[row][col] != color) {
                grid[i][j] = -2;
            }
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == color) {
                dfs(grid, row, col);
            }
        }
        grid[i][j] = grid[i][j] == -2 ? -2 : color;
    }
}
*/
