package sort;

import java.util.*;

//这是discuss里别人巧妙的思路：
//有一堆点 求一个点到所有点的距离和最小 应该就是这些点的median中位数 用这个思路拓展

public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row.add(i);
                col.add(j);
            }
        }
        return getMin(row) + getMin(col);
    }
    private int getMin(List<Integer> list) {
        Collections.sort(list);
        int i = 0;
        int j = list.size() - 1;
        int res = 0;
        while (i < j) {
            res += list.get(j--) - list.get(i++);
        }
        return res;
    }
}





//用BFS探索每个格子到1的距离会超时
/*
public class BestMeetingPoint {
    private int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    boolean[][] visited = new boolean[m][n];
                    Queue<int[]> Q = new LinkedList<>();
                    Q.offer(new int[]{i,j});
                    visited[i][j] = true;
                    int level = 0;
                    while (!Q.isEmpty()) {
                        int size = Q.size();
                        level ++;
                        for (int k = 0; k < size; k++) {
                            int[] cur = Q.poll();
                            for (int[] dir : dirs) {
                                int row = cur[0] + dir[0];
                                int col = cur[1] + dir[1];
                                if (row < 0 || col < 0 || row >= m || col >= n ||
                                        visited[row][col]) {
                                    continue;
                                }
                                distance[row][col] += level;
                                visited[row][col] = true;
                                Q.offer(new int[]{row, col});
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.min(res, distance[i][j]);
            }
        }
        return res;
    }
}
*/