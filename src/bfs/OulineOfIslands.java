package bfs;

//1表示陆地 0表示海水 保留岛的轮廓 挖空中间的陆地
//可以check整个数组一遍 如果周围有0就是有海水 标记一下 标记过的变成1 1变成0

import java.util.LinkedList;
import java.util.Queue;

public class OulineOfIslands {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public void outlineOfIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            Q.offer(new int[]{i,-1});
            Q.offer(new int[]{i,n});
        }
        for (int i = 0; i < n; i++) {
            Q.offer(new int[]{-1, i});
            Q.offer(new int[]{m, i});
        }
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            for (int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                if (row < 0 || col < 0 || row >= m || col >= n) {
                    continue;
                }
                if (grid[row][col] == 1) {
                    grid[row][col] = 2;
                    continue;
                }
                if (grid[row][col] == 0) {
                    grid[row][col] = 3;
                    Q.offer(new int[]{row, col});
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                } else if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                } else if (grid[i][j] == 3) {
                    grid[i][j] = 0;
                }
            }
        }
    }
}
