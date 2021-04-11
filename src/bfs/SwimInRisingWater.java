package bfs;

import java.util.PriorityQueue;

public class SwimInRisingWater {
    class Point {
        int x;
        int y;
        int time;
        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int max = 0;
        boolean[][] settled = new boolean[n][n];
        PriorityQueue<Point> pq =  new PriorityQueue<>((a,b) -> a.time - b.time);
        pq.add(new Point(0, 0, 0));
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            max = Math.max(max, p.time);
            settled[p.x][p.y] = true;
            if (p.x == n-1 && p.y == n-1) {
                return max;
            }
            for (int[] dir : dirs) {
                int row = p.x+dir[0];
                int col = p.y+dir[1];
                if (row < 0 || col < 0 || row >= n || col >= n || settled[row][col]) {
                    continue;
                }
                pq.add(new Point(row, col, Math.max(p.time, grid[row][col])));
            }
        }
        return max;
    }
}

/*
class Solution {
    class wrapper {
        int x;
        int y;
        int max;
        public wrapper(int x, int y, int max) {
            this.x = x;
            this.y = y;
            this.max = max;
        }
    }
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int swimInWater(int[][] grid) {
        PriorityQueue<wrapper> pq = new PriorityQueue<>((a,b) -> a.max - b.max);
        pq.offer(new wrapper(0, 0, grid[0][0]));
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            wrapper cur = pq.poll();
            int max = cur.max;
            for (int[] dir : dirs) {
                int row = dir[0] + cur.x;
                int col = dir[1] + cur.y;
                if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col]) {
                    continue;
                }
                visited[row][col] = true;
                int curMax = Math.max(max, grid[row][col]);
                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    return curMax;
                }
                pq.offer(new wrapper(row, col, curMax));
            }
        }
        return -1;
    }
}
 */