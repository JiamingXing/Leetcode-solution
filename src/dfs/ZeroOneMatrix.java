package dfs;

import java.util.LinkedList;
import java.util.Queue;

//可以不用额外的space吗  就是在原matrix的基础上进行修改？
//先遍历一遍matrix  如果是0进Q 如果不是0先更新成Integer.MAX_VALUE
//然后做BFS 通过distance来避免重复访问 就不用visited了 这么一算下来节省了 2个matrix的空间...
//

public class ZeroOneMatrix {
    private static final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    Q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            for (int[] dir : dirs) {
                int row = cur[0] + dir[0];
                int col = cur[1] + dir[1];
                if (row < 0 || col < 0 || row >= m || col >= n
                        || visited[row][col]) {
                    continue;
                }
                res[row][col] = res[cur[0]][cur[1]] + 1;
                visited[row][col] = true;
                Q.add(new int[]{row, col});
            }
        }
        return res;
    }
}

//节省了很多space这种做法
//当然不用去判断小于 如果某个cell的value不是MAX 那么我们就可以跳过 这样也可以
/*
public class Solution {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.offer(new int[] {i, j});
                }
                else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n ||
                        matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1) continue;
                queue.add(new int[] {r, c});
                matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
            }
        }

        return matrix;
    }
}
 */
