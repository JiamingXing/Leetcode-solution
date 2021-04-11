package dfs;

import java.util.Arrays;
import java.util.PriorityQueue;

//2020/3/16 写的一遍AC BFS+PQ 类似Dijkstra's Algorithm版本
//我觉得这个版本写的是最喜欢的把当前

public class TheMazeII {
    class Point {
        int x;
        int y;
        int path;
        public Point(int x, int y, int path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        boolean[][] settled = new boolean[m][n];
        dist[start[0]][start[1]] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b)->a.path-b.path);
        pq.add(new Point(start[0], start[1], 0));
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.x == destination[0] && p.y == destination[1]) {
                return dist[p.x][p.y];
            }
            if (settled[p.x][p.y]) {
                continue;
            }
            settled[p.x][p.y] = true;
            for (int[] dir : dirs) {
                int row = p.x, col = p.y, step = 0;
                while (row + dir[0] >= 0 && col + dir[1] >= 0
                        && row + dir[0] < m && col + dir[1] < n
                        && maze[row+dir[0]][col+dir[1]] == 0) {
                    row += dir[0];
                    col += dir[1];
                    step ++;
                }
                if (!settled[row][col]) {
                    if (dist[p.x][p.y] + step < dist[row][col]) {
                        dist[row][col] = dist[p.x][p.y] + step;
                        pq.add(new Point(row, col, dist[row][col]));
                    }
                }
            }
        }
        return -1;
    }
}

//public class TheMazeII {
//    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
//    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//        int m = maze.length;
//        int n = maze[0].length;
//        int[][] dist = new int[m][n];
//        //正确的做法 dist[start[0]][start[1]] = 1
//        dfs(maze, start, destination, dist);
//        //return dist[][] - 1;
//        //还没搞懂为什么。。。
//        return dist[destination[0]][destination[1]];
//    }
//    private void dfs(int[][] maze, int[] cur, int[] dst, int[][] dist) {
//        if (cur[0] == dst[0] && cur[1] == dst[1]) {
//            return;
//        }
//        for (int[] dir : dirs) {
//            int row = cur[0] + dir[0];
//            int col = cur[1] + dir[1];
//            if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] == 1) {
//                continue;
//            }
//            int x = cur[0];
//            int y = cur[1];
//            int len = dist[x][y];
//            while (x + dir[0] >= 0 && x + dir[0] < maze.length &&
//                    y + dir[1] >= 0 && y + dir[1] < maze[0].length &&
//                    maze[x + dir[0]][y + dir[1]] == 0) {
//                len ++;
//                x += dir[0];
//                y += dir[1];
//            }
//            if (dist[x][y] > 0 && len >= dist[x][y]) {
//                continue;
//            }
//            dist[x][y] = len;
//            int[] num = new int[]{x, y};
//            dfs(maze, num, dst, dist);
//        }
//    }
//}



//BFS的做法 一开始自己想的是只有一个dist数组记录这个点的最短路径 然后你每次BFS加几个点进来如果后面的点到当前的点
//长度小的话就会有很多重复操作
//也想到了用priority queue 每次先poll 从起点到当前点长度最小的点 但是当时想的是用pq的话 我遇到重点就直接break
//这样是不对的 因为有可能开始距离大的点最后到重点的距离反而小 所以一是不能提前break 二是我们想要排除重复的情况 还是要用一个dist数组记录
//pq+dist数组记录最短路径才能保证我们尽可能减少重复操作

//为什么会想到用PQ？先poll小的出来有机会去更新后面的点？
//不用PQ用普通的Queue是可以pass的
//用BFS一定会走遍 board中所有可能的点 因为通向destination不一定只有一种可能路径 你要去check所有可能
/*
class Solution {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    private class Point {
        int x;
        int y;
        int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.dist - p2.dist;
            }
        });
        pq.offer(new Point(start[0], start[1], 0));
        while (!pq.isEmpty()) {
            Point point = pq.poll();
            if (dist[point.x][point.y] <= point.dist) {
                continue;
            }
            dist[point.x][point.y] = point.dist;
            for (int[] dir : dirs) {
                int x = point.x;
                int y = point.y;
                int step = 0;
                while (x+dir[0] >= 0 && y+dir[1] >= 0 && x+dir[0] < m && y+dir[1] < n && maze[x+dir[0]][y+dir[1]] == 0) {
                    x += dir[0];
                    y += dir[1];
                    step ++;
                }
                pq.offer(new Point(x, y, point.dist+step));
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}
*/


//自己写的DFS TLE。。。
/*
class Solution {
    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[start[0]][start[1]] = 0;
        dfs(maze, start[0], start[1], destination, dist);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
    private void dfs(int[][] maze, int row, int col, int[] dst, int[][] dist) {
        if (row == dst[0] && col == dst[1]) {
            return;
        }
        for (int[] dir : dirs) {
            int x = row;
            int y = col;
            int len = dist[x][y];
            while (x + dir[0] >= 0 && x + dir[0] < maze.length &&
                    y + dir[1] >= 0 && y + dir[1] < maze[0].length &&
                    maze[x + dir[0]][y + dir[1]] == 0) {
                len ++;
                x += dir[0];
                y += dir[1];
            }
            if (len >= dist[x][y]) {
                continue;
            }
            dist[x][y] = len;
            dfs(maze, x, y, dst, dist);
        }
    }
}
*/


//------------------------------------
//二刷写的DFS+BFS
/*
class Solution {
    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        helper(maze, dist, start);
        return dist[destination[0]][destination[1]]
                == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
    private void helper(int[][] maze, int[][] dist, int[] cur) {
        for (int[] dir : dirs) {
            int path = 0;
            int row = cur[0], col = cur[1];
            while (row+dir[0] >= 0 && col+dir[1] >= 0
                    && row+dir[0] < maze.length && col+dir[1] < maze[0].length
                    && maze[row+dir[0]][col+dir[1]] == 0) {
                row += dir[0];
                col += dir[1];
                path ++;
            }
            int curDist = dist[cur[0]][cur[1]] + path;
            if (curDist < dist[row][col]) {
                dist[row][col] = curDist;
                helper(maze, dist, new int[]{row,col});
            }
        }
    }
}
 */

