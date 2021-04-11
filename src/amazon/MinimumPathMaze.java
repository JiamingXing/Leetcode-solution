package amazon;

//target = 9
//什么时候把1变成0 表示访问过了

import java.util.LinkedList;
import java.util.Queue;

public class MinimumPathMaze {
    private static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int minPath(int[][] maze) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int res = 0;
        //put here?
        maze[0][0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res ++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int row = cur[0] + dir[0];
                    int col = cur[1] + dir[1];
                    if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 0) {
                        continue;
                    }
                    if (maze[row][col] == 9) {
                        return res;
                    }
                    q.offer(new int[]{row, col});
                    maze[row][col] = 0;
                }
            }
        }
        return 0;
    }
}
