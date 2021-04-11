package dfs;

//这是一道google的面经 就是给你一个迷宫 一个起点一个终点 并且最多能炸开K个墙
//问你是否可以从起点到终点
//我觉得思路比较直接用DFS 问题在于如何处理炸开K个墙
//第一时间想到的是如果碰到墙我们可以选择 炸或不炸
//但是可能代码写起来还是有点犹豫的
//顺便考虑下允许重复访问吗？
//每到这个时候总感觉自己对 DFS的理解还不够透彻。。
//我觉得什么时候你能完全理解google的扫地机器人那道题 DFS理解就会高很多了


//这样写对吗？
//好像没有定义重复访问..比如我往左右走了还会往右边走回来
//我加个visited 代码还会对吗...其实就是对DFS理解好像有点问题？

public class MazeWithAtMostKWalls {
    private final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public boolean canReachDestination(int[][] grid, int[] start, int[] end, int K) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, visited, start[0], start[1], end, K);
    }
    private boolean dfs(int[][] grid, boolean[][] visited, int x, int y, int[] end, int K) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return false;
        }
        if (visited[x][y]) {
            return false;
        }
        if (x == end[0] && y == end[1]) {
            return true;
        }
        if (grid[x][y] == 0 && K == 0) {
            return false;
        }
        visited[x][y] = true;
        for (int[] dir : dirs) {
            int row = dir[0] + x;
            int col = dir[1] + y;
            if (dfs(grid, visited, row, col, end, grid[x][y] == 0 ? K-1:K)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}
