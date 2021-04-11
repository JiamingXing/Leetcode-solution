package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestBridge(int[][] A) {
        int m = A.length, n = A[0].length;
        int x = 0, y = 0;
        int res = 0;
        //find first 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        //populate first island and push border to Q
        Queue<Point> Q1 = new LinkedList<>();
        Queue<Point> Q2 = new LinkedList<>();
        Q1.add(new Point(x,y));
        A[x][y] = -1;
        while (!Q1.isEmpty()) {
            Point p = Q1.poll();
            boolean isBorder = false;
            for (int[] dir : dirs) {
                int row = p.x + dir[0];
                int col = p.y + dir[1];
                if (row < 0 || col < 0 || row >= m || col >= n || A[row][col] == -1) {
                    continue;
                }
                if (A[row][col] == 0) {
                    isBorder = true;
                    continue;
                }
                if (A[row][col] == 1) {
                    A[row][col] = -1;
                    Q1.add(new Point(row, col));
                }
            }
            if (isBorder) {
                Q2.add(new Point(p.x, p.y));
            }
        }
        while (!Q2.isEmpty()) {
            int size = Q2.size();
            for (int i = 0; i < size; i++) {
                Point p = Q2.poll();
                for (int[] dir : dirs) {
                    int row = dir[0] + p.x;
                    int col = dir[1] + p.y;
                    if (row < 0 || col < 0 || row >= m || col >= n || A[row][col] == -1) {
                        continue;
                    }
                    if (A[row][col] == 1) {
                        return res;
                    }
                    if (A[row][col] == 0) {
                        A[row][col] = -1;
                        Q2.add(new Point(row, col));
                    }
                }
            }
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {
        ShortestBridge s = new ShortestBridge();
        int[][] A = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
        System.out.println(s.shortestBridge(A));
    }
}
