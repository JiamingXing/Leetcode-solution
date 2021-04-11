package unionfind;

import java.util.Arrays;

//union find做法 探测到1和周围4个方向是1的格子union一下 如果之前不相连就count--

public class NumberofIslands {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m*n];
        Arrays.fill(parent, -1);
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res ++;
                    for (int[] dir : dirs) {
                        int row = i + dir[0];
                        int col = j + dir[1];
                        if (row >= 0 && col >=0 && row< m && col < n && grid[row][col] == '1') {
                            int id1 = i*n + j;
                            int id2 = row*n + col;
                            int r1 = find(parent, id1);
                            int r2 = find(parent, id2);
                            if (r1 == r2) {
                                continue;
                            }
                            parent[r1] = r2;
                            res --;
                        }
                    }
                }
            }
        }
        return res;
    }
    private int find(int[] parent, int id) {
        if (parent[id] == -1) {
            return id;
        }
        parent[id] = find(parent, parent[id]);
        return parent[id];
    }
}
