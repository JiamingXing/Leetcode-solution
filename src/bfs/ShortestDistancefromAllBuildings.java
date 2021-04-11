package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int numBuilding = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numBuilding ++;
                    Queue<int[]> Q = new LinkedList<>();
                    Q.offer(new int[]{i,j});
                    boolean[][] visited = new boolean[m][n];
                    int dist = 0;
                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();
                        dist ++;
                        for (int[] dir : dirs) {
                            int row = cur[0] + dir[0];
                            int col = cur[1] + dir[1];
                            if (row < 0 || col < 0 || row >= m || col >= n || visited[row][col] || grid[row][col] != 0) {
                                continue;
                            }
                            distance[row][col] = dist;
                            reach[row][col] ++;
                            Q.offer(new int[]{row, col});
                            visited[row][col] = true;
                        }
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numBuilding) {
                    res = Math.min(res, distance[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
