package bfs;

import java.util.LinkedList;
import java.util.Queue;


//这里在确定完当前poll出来得点是什么状态之后 我们再遍历一遍每个点所有相邻的点如果是E 就先标记为B不管之后是什么状态
//这样可以避免不同的点重复添加BFS 还有一个点是当我某个点在附近检测到右M之后 我就不继续进行BFS了？这是题目规定的
//只有当某个点周围没有地雷的时候标记为B 并且recursive处理周围所有的点 但是如果有地雷标记为地雷数量不继续BFS！
public class MiniSweeper {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<int[]> Q = new LinkedList<>();
        int m = board.length;
        int n = board[0].length;
        Q.offer(click);
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            if (board[cur[0]][cur[1]] == 'M') {
                board[cur[0]][cur[1]] = 'X';
            } else {
                int count = 0;
                for (int[] dir : dirs) {
                    int row = dir[0] + cur[0];
                    int col = dir[1] + cur[1];
                    if (row < 0 || col < 0 || row >= m || col >= n) {
                        continue;
                    }
                    if (board[row][col] == 'M') {
                        count ++;
                    }
                }
                if (count > 0) {
                    board[cur[0]][cur[1]] = (char)(count+'0');
                } else {
                    board[cur[0]][cur[1]] = 'B';
                    for (int[] dir : dirs) {
                        int row = cur[0] + dir[0];
                        int col = cur[1] + dir[1];
                        if (row < 0 || col < 0 || row >= m || col >= n) {
                            continue;
                        }
                        if (board[row][col] == 'E') {
                            Q.offer(new int[]{row, col});
                            //避免重复添加
                            board[row][col] = 'B';
                        }
                    }
                }
            }
        }
        return board;
    }
}

