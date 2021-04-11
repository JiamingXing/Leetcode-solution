package dfs;

//其实不难的一道题 属于DFS的常规题 但是看到hard难度 加上比较新的条件(要求走过所有可以走的点) 有点被吓住了...

//因为一开始 这个count和num的关系没搞清楚导致case 没过 所有print了路径来debug
//思路就很简单了 DFS 每次把当前的走过的0个数传进去 并且标记走过的不走回头路backtrack的时候标记回来...

import java.util.ArrayList;
import java.util.List;

public class UniquePathsIII {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    private final String[] direction = {"down", "up", "right", "left"};
    int res;
    public List<List<String>> uniquePathsIII(int[][] grid) {
        int count = 0;
        int startX = 0;
        int startY = 0;
        res = 0;
        List<List<String>> detail = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 0) {
                    count ++;
                }
            }
        }
        dfs(grid, startX, startY, 0, count+1, detail, new ArrayList<>());
        return detail;
    }
    private void dfs(int[][] grid, int i, int j, int num, int count, List<List<String>> detail, List<String> temp) {
        if (grid[i][j] == 2) {
            if (num == count) {
                res ++;
                detail.add(new ArrayList<>(temp));
            }
            return;
        }
        grid[i][j] = -2;
//        for (int[] dir : dirs) {
//            int row = dir[0] + i;
//            int col = dir[1] + j;
//            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && (grid[row][col] == 0 || grid[row][col] == 2)) {
//                dfs(grid, row, col, num+1, count);
//            }
//        }
        for (int k = 0; k < 4; k++) {
            int row = dirs[k][0] + i;
            int col = dirs[k][1] + j;
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && (grid[row][col] == 0 || grid[row][col] == 2)) {
                temp.add(direction[k]);
                dfs(grid, row, col, num+1, count, detail, temp);
                temp.remove(temp.size()-1);
            }
        }
        grid[i][j] = 0;
    }
}

/*
class Solution {
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    //int res;
    public int uniquePathsIII(int[][] grid) {
        int count = 0;
        int startX = 0;
        int startY = 0;
        //res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 0) {
                    count ++;
                }
            }
        }
        return dfs(grid, startX, startY, 0, count+1);
        //return res;
    }
    private int dfs(int[][] grid, int i, int j, int num, int count) {
        if (grid[i][j] == 2) {
            if (num == count) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        grid[i][j] = -2;
        for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && (grid[row][col] == 0 || grid[row][col] == 2)) {
                res += dfs(grid, row, col, num+1, count);
            }
        }
        grid[i][j] = 0;
        return res;
    }
}
 */