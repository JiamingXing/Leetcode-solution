package com.jimmy.dfs;

import java.util.LinkedList;
import java.util.Queue;


//这道题思路不难但是关键还是在于理解题意
//如果能想清楚这道题的搜索思路 就会很简单
//1.点到M的话 直接变成X结束游戏
//2.点到E 先判断周围有没有M 有的话变成M数量 结束 如果没有那么我们把周围8个方向中是E的加进来进行下一步搜索 变成B防止重复添加

public class Minesweeper {
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








//DFS版本
/*
public class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        if (board[row][col] == 'M') { // Mine
            board[row][col] = 'X';
        }
        else { // Empty
            // Get number of mines first.
            int count = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    int r = row + i, c = col + j;
                    if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                }
            }

            if (count > 0) { // If it is not a 'B', stop further DFS.
                board[row][col] = (char)(count + '0');
            }
            else { // Continue DFS to adjacent cells.
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[] {r, c});
                    }
                }
            }
        }

        return board;
    }
}
*/