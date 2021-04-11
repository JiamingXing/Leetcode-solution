package dfs;

//number of Islands 花样变种
//其实这道题一开始你卡了就真的有点菜
//题目意思翻译成 如果island不和边界相连就是closed
//那么你要不就从边界的0开始统计unclosed island 但是你同样需要统计closed island
//你直接在dfs的时候加一个boolean  如果碰到了边界就return false表示unclosed
//因为这里对于一个island  你要走过所有的0 所以不要着急return

public class NumberofClosedIslands {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    res ++;
                }
            }
        }
        return res;
    }
    private boolean dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return false;
        }
        if (grid[x][y] == 1) {
            return true;
        }
        grid[x][y] = 1;
        boolean res = true;
        for (int[] dir : dirs) {
            int row = dir[0] + x;
            int col = dir[1] + y;
            res = res & dfs(grid, row, col);
        }
        return res;
    }
}
