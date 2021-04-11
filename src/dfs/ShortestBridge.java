package dfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    private final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestBridge(int[][] A) {
        //try to add one iasland's border to Q... do BFS
        //find first 1... start from this to do BFS.... if one square is border add to another Q
        //border is adjacent to 0...
        int startX = 0;
        int startY = 0;
        //find first 1
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        //BFS find whole island, flip to 2, also add to another Q if 0 adjacent
        Queue<int[]> Q1 = new LinkedList<>();
        Queue<int[]> Q2 = new LinkedList<>();
        Q1.offer(new int[]{startX, startY});
        while (!Q1.isEmpty()) {
            int[] cur = Q1.poll();
            A[cur[0]][cur[1]] = 2;
            boolean isBord = false;
            for (int[] dir : dirs) {
                int row = dir[0] + cur[0];
                int col = dir[1] + cur[1];
                if (row >= 0 && row < A.length && col >= 0 && col < A[0].length && A[row][col] == 0) {
                    isBord = true;
                }
                if (row >= 0 && row < A.length && col >= 0 && col < A[0].length && A[row][col] == 1) {
                    Q1.offer(new int[]{row, col});
                }

            }
            if (isBord) {
                Q2.offer(new int[]{cur[0], cur[1]});
            }
        }
        int res = 0;
        while (Q2.isEmpty()) {
            int size = Q2.size();
            res ++;
            for (int i = 0; i < size; i++) {
                int[] cur = Q2.poll();
                for (int[] dir : dirs) {
                    int row = dir[0] + cur[0];
                    int col = dir[1] + cur[1];
                    if (row >= 0 && row < A.length && col >= 0 && col < A[0].length && A[row][col] == 0) {
                        Q2.offer(new int[]{row, col});
                        A[row][col] = 2;
                    }
                }
            }
        }
        return res;
    }
}
