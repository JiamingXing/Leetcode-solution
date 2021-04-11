package twopointers;

//这是一道很典型的 被应用题外表唬住不敢下手的题
//其实就是按照游戏规则写程序就可以了
//按照游戏规则：先根据当前状态下的board 判断每个点去确认这个点是不是要消除

//这里有一个很关键的地方在于，当你拿到像这样陌生的题目的时候 真的不要去想一些骚套路，不要觉得每个点判断很浪费时间
//特别是当你判断一个点会被消除的时候 不要想着相连的点同时记录 这样反而会给你的思维造成很大的困扰
//按照逻辑遍历数组挨个判断 面试的时候也是一样大不了这是一个brute-force的做法 再根据面试官的提示进行改进

//接下来记录玩会被消掉的点之后 把他们都变成0之后 drop这些0 然后继续做同样的操作直到stable为止
//这里drop只用two pointer从下到上进行遍历就可以了 思路就是283. Move Zeros 有一个insertPos
//这么看来其实很简单的一道题 就是考查题目理解以及分析细心的能力 不要惧怕这种应用题 照常做就行了

import java.util.HashSet;
import java.util.Set;

public class CandyCrush {
    public int[][] candyCrush(int[][] board) {
        Set<int[]> set = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = board[i][j];
                if (cur == 0) {
                    continue;
                }
                //这一步去判断说 有没有连续三个...是否消除 如果自己想的话可能会想到用BFS或者DFS去做
                //简单粗暴的去判断一下就好了 当前index为中心 判断三个格子...
                if (j >= 2 && board[i][j-2] == cur && board[i][j-1] == cur ||
                        j <= n-3 && board[i][j+1] == cur && board[i][j+2] == cur ||
                        i >= 2 && board[i-2][j] == cur && board[i-1][j] == cur ||
                        i <= m-3 && board[i+1][j] == cur && board[i+2][j] == cur ||
                        j >= 1 && j <= n-2 && board[i][j-1] == cur && board[i][j+1] == cur ||
                        i >= 1 && i <= m-2 && board[i-1][j] == cur && board[i+1][j] == cur)
                {
                    set.add(new int[]{i, j});
                }
            }
        }
        if (set.isEmpty()) {
            return board;
        }
        for (int[] temp : set) {
            int row = temp[0];
            int col = temp[1];
            board[row][col] = 0;
        }
        drop(board);
        return candyCrush(board);
    }
    private int[][] drop(int[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            int bot = board.length - 1;
            int iter = board.length - 1;
            while (iter >= 0) {
                if (board[iter][j] == 0) {
                    iter --;
                } else {
                    board[bot--][j] = board[iter--][j];
                }
            }
            while (bot >= 0) {
                board[bot--][j] = 0;
            }
        }
        return board;
    }
}
