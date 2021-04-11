package dfs;

//给一个起始点开始挖金矿，有一些事障碍物 然后不能走回头路 问最多能挖到多少？

public class GoldMiner {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int maximumGold(char[][] grid, int x , int y) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] maxGold = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, x, y, maxGold, visited);
    }
    private int dfs(char[][] grid, int x, int y, int[][] maxGold, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 'X') {
            return 0;
        }
        if (visited[x][y]) {
            return maxGold[x][y];
        }
        visited[x][y] = true;
        int max = 0;
        for (int[] dir : dirs) {
            int row = dir[0] + x;
            int col = dir[1] + y;
            max = Math.max(max, dfs(grid, row, col, maxGold, visited));
        }
        max += (int)grid[x][y];
        maxGold[x][y] = max;
        return max;
    }
}
