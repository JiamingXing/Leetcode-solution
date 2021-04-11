package dfs;

//自己写的还不知道对不对

import java.util.Arrays;

//二刷自己写的有一个case过不了
//感觉可能是相同距离但是比较lexi的时候出问题
//考虑到如果前面到某个点不同路径距离相同本来是直接跳过的 但是现在好像需要考虑 因为lexi更小可能
//但是如果变成 <= memory就会溢出..递归太多了

public class TheMazeIII {
    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    String res = "";
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[ball[0]][ball[1]] = 0;
        helper(maze, dist, ball, "", hole);
        return res;
    }
    private void helper(int[][] maze, int[][] dist, int[] cur, String path, int[] hole) {
        for (int i = 0; i < dirs.length; i++) {
            int[] dir = dirs[i];
            int step = 0;
            int row = cur[0], col = cur[1];
            String curPath = "";
            switch (i) {
                case 0 :
                    curPath = path + "d";
                    break;
                case 1 :
                    curPath = path + "u";
                    break;
                case 2:
                    curPath = path + "r";
                    break;
                case 3:
                    curPath = path + "l";
                    break;
            }
            while (row+dir[0] >= 0 && row+dir[0]<maze.length
            && col+dir[1] >= 0 && col+dir[1]<maze[0].length
            && maze[row+dir[0]][col+dir[1]] == 0) {
                step ++;
                row += dir[0];
                col += dir[1];
                if (row == hole[0] && col == hole[1]) {
                    if (dist[cur[0]][cur[1]] + step < dist[hole[0]][hole[1]]
                    || (dist[cur[0]][cur[1]] + step == dist[hole[0]][hole[1]] && res.compareTo(curPath) > 0)) {
                        res = curPath;
                        dist[hole[0]][hole[1]] = dist[cur[0]][cur[1]] + step;
                    }
                    //不应该return 应该继续...
                    return;
                }
            }
            if (dist[cur[0]][cur[1]] + step < dist[row][col]) {
                dist[row][col] = dist[cur[0]][cur[1]] + step;
                helper(maze, dist, new int[]{row, col}, curPath, hole);
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {0,1};
        TheMazeIII t = new TheMazeIII();
        System.out.println(t.findShortestWay(maze, ball, hole));
    }
}

//2020/2/16 写的pass的版本但是写了很多错误出来
//其实整道题的思路 很清楚直接 如果我们是DFS 那么roll到某个点 dist比当前小于等于的话就继续(等于是因为可能存在lexi更小的path)
//在roll的过程中 多加一层判断 就是我们roll到了hole就直接结束当前的DFS 不再往下了 if holdFound continue...
//如果是BFS的话 应该也差不多
/*
class Solution {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[ball[0]][ball[1]] = 0;
        dfs(maze, dist, ball, hole, "", temp);
        Collections.sort(temp);
        return temp.isEmpty() ? "impossible" : temp.get(0);
    }
    private void dfs(int[][] maze, int[][] dist, int[] cur, int[] hole, String path, List<String> temp) {
        for (int i = 0; i < dirs.length; i++) {
            int[] dir = dirs[i];
            int step = 0;
            int row = cur[0], col = cur[1];
            String curPath = "";
            boolean holeFound = false;
            switch(i) {
                case 0 :
                    curPath = path + "d";
                    break;
                case 1 :
                    curPath = path + "u";
                    break;
                case 2:
                    curPath = path + "r";
                    break;
                case 3:
                    curPath = path + "l";
                    break;
            }
            while (row+dir[0] >= 0 && col+dir[1] >= 0 && row+dir[0]<maze.length &&
                    col+dir[1] < maze[0].length && maze[row+dir[0]][col+dir[1]] == 0) {
                step ++;
                row += dir[0];
                col += dir[1];
                if (row == hole[0] && col == hole[1]
                        && dist[cur[0]][cur[1]] + step <= dist[hole[0]][hole[1]]) {
                     //小于的时候要clear你之前的list...并且你必须在更新dist之前clear
                    if (dist[cur[0]][cur[1]] + step < dist[hole[0]][hole[1]]) {
                        temp.clear();
                    }
                    dist[row][col] = dist[cur[0]][cur[1]] + step;
                    temp.add(curPath);
                    holeFound = true;
                }
            }
            if (holeFound) {
                continue;
            }
            //stay at same position. continue
            if (row == cur[0] && col == cur[1]) {
                continue;
            }
            if (dist[cur[0]][cur[1]] + step <= dist[row][col]) {
                dist[row][col] = dist[cur[0]][cur[1]] + step;
                dfs(maze, dist, new int[]{row, col}, hole, curPath, temp);
            }
        }
    }
}
*/


/*
public class TheMazeIII {
    private static  final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    String res = "";
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        dist[ball[0]][ball[1]] = 1;
        dfs(ball[0], ball[1], "", maze, dist, hole);
        return res;
    }
    private void dfs(int row, int col, String path, int[][] maze, int[][] dist, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        boolean holeFound = false;
        for (int i = 0; i < dirs.length; i++) {
            int x = row;
            int y = col;
            int p = dirs[i][0];
            int r = dirs[i][1];
            int len = dist[x][y];
            String curDir = "";
            if (p == 1) {
                curDir += "d";
            } else if (p == -1) {
                curDir += "u";
            } else if (r == 1) {
                curDir += "r";
            } else {
                curDir += "l";
            }
            while (x+p >= 0 && x+p < m && y+r >= 0 && y+r < n
                    && maze[x+p][y+r] == 0) {
                len ++;
                x += p;
                y += r;
                if (x == hole[0] && y == hole[1]) {
                    if (dist[hole[0]][hole[1]] > len) {
                        dist[hole[0]][hole[1]] = len;
                        res = path + curDir;
                    }
                    holeFound = true;
                    break;
                }
            }
            if (holeFound) {
                break;
            }
            if (dist[x][y] > 0 && len >= dist[x][y]) {
                continue;
            }
            dist[x][y] = len;
            dfs(x, y, res+curDir, maze, dist, hole);
        }
    }
}
 */




//别人的代码可以学习一下
/*
public class Solution {
    int minStep;
    int m, n;
    String res;
    int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    String[] dirc = {"d", "r", "l", "u"}; // 0123
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.m = maze.length;
        this.n = maze[0].length;
        this.minStep = Integer.MAX_VALUE;
        this.res = null;
        boolean[][] vis = new boolean[m][n];
        vis[ball[0]][ball[1]] = true;

        dfs(ball[0], ball[1], maze, hole, vis, "", 0);

        return res == null ? "impossible" : res;
    }

    private void dfs(int i, int j, int[][] maze, int[] hole, boolean[][] vis, String route, int step) {
        if (step > minStep) return;
        if (i == hole[0] && j == hole[1]) {
            if (step == minStep && route.compareTo(res) < 0) {
                res = route;
            } else if (step < minStep) {
                minStep = step;
                res = route;
            }
            vis[i][j] = false;
            return;
        }

        for (int d = 0; d < 4; d++) {
            // roll to the wall
            int x = i, y = j;
            while (x + dirs[d][0] >= 0 && x + dirs[d][0] < m && y + dirs[d][1] >= 0 && y + dirs[d][1] < n
                    && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                x += dirs[d][0];
                y += dirs[d][1];
                if (x == hole[0] && y == hole[1] || vis[x][y])  break;
            }
            if (!vis[x][y] && maze[x][y] == 0) {
                vis[x][y] = true;
                dfs(x, y, maze, hole, vis, route + dirc[d], step + Math.abs(x - i) + Math.abs(y - j));
                vis[x][y] = false;
            }
        }
    }
}
*/


//BFS
/*
public class Solution {
    class Point implements Comparable<Point> {
        int x,y,l;
        String s;
        public Point(int _x, int _y) {x=_x;y=_y;l=Integer.MAX_VALUE;s="";}
        public Point(int _x, int _y, int _l,String _s) {x=_x;y=_y;l=_l;s=_s;}
        public int compareTo(Point p) {return l==p.l?s.compareTo(p.s):l-p.l;}
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m=maze.length, n=maze[0].length;
        Point[][] points=new Point[m][n];
        for (int i=0;i<m*n;i++) points[i/n][i%n]=new Point(i/n, i%n);
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        String[] ds=new String[] {"u","r","d","l"};
        PriorityQueue<Point> list=new PriorityQueue<>(); // using priority queue
        list.offer(new Point(ball[0], ball[1], 0, ""));
        while (!list.isEmpty()) {
            Point p=list.poll();
            if (points[p.x][p.y].compareTo(p)<=0) continue; // if we have already found a route shorter
            points[p.x][p.y]=p;
            for (int i=0;i<4;i++) {
                int xx=p.x, yy=p.y, l=p.l;
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0 && (xx!=hole[0] || yy!=hole[1])) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                    l++;
                }
                if (xx!=hole[0] || yy!=hole[1]) { // check the hole
                    xx-=dir[i][0];
                    yy-=dir[i][1];
                    l--;
                }
                list.offer(new Point(xx, yy, l, p.s+ds[i]));
            }
        }
        return points[hole[0]][hole[1]].l==Integer.MAX_VALUE?"impossible":points[hole[0]][hole[1]].s;
    }
}
*/