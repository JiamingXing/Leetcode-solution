package dfs;

import java.util.Arrays;

public class TheMaze {
    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Arrays.equals(start, destination);
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(maze, start, visited, dirs, destination);
    }
    private boolean dfs(int[][] maze, int[] cur, boolean[][] visited, int[][] dirs, int[] dst) {
        if (visited[cur[0]][cur[1]]) {
            return false;
        }
        if (cur[0] == dst[0] && cur[1] == dst[1]) {
            return true;
        }
        visited[cur[0]][cur[1]] = true;
        for (int[] dir : dirs) {
            int row = cur[0] + dir[0];
            int col = cur[1] + dir[1];
            if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] == 1) {
                continue;
            }
            int[] temp = keepGoing(cur[0], cur[1], dir, maze);
            if (dfs(maze, temp, visited, dirs, dst)) {
                return true;
            }
        }
        return false;
    }
    private int[] keepGoing(int x, int y, int[] dir, int[][] maze) {
        while (x + dir[0] >= 0 && x + dir[0] < maze.length &&
                y + dir[1] >= 0 && y + dir[1] < maze[0].length &&
                maze[x + dir[0]][y + dir[1]] == 0) {
            x += dir[0];
            y += dir[1];
        }
        return new int[]{x, y};
    }
}



//BFS version
/*
class Solution {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return false;
        }
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(start);
        visited[start[0]][start[1]] = true;
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            for (int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && maze[row][col] == 0) {
                    int[] next = rolling(maze, cur, dir);
                    if (next[0] == destination[0] && next[1] == destination[1]) {
                        return true;
                    }
                    if (!visited[next[0]][next[1]]) {
                        Q.offer(next);
                        visited[next[0]][next[1]] = true;
                    }
                }
            }
        }
        return false;
    }
    private int[] rolling(int[][] maze, int[] cur, int[] dir) {
        int m = maze.length;
        int n = maze[0].length;
        int row = cur[0];
        int col = cur[1];
        while (row+dir[0] >= 0 && row+dir[0] < m && col+dir[1] >= 0 && col+dir[1] < n && maze[row+dir[0]][col+dir[1]] == 0) {
            row += dir[0];
            col += dir[1];
        }
        int[] res = {row, col};
        return res;
    }
}

 */
