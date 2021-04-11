package hashtable;

//这道题以后复习永远都是重点关注对象 因为你自己写的时候竟然可以犯编程中的原则性错误 几乎不可原谅的...

//how to define a unique for different islands
//在思考如何define这个unique key的时候 会被一种想法干扰 就是我这个岛形状任意
//那么我们每次在用BFS/DFS遍历得到一个新的岛的时候可能从中间某个点出发？
//但是仔细想想我们的算法思路 就知道 我们扫面grid的时候，每次对两个不同岛 如果他们形状相同 肯定从同一个点开始遍历
//比如都从岛最坐上的点...
//那么同理 如果从某一个点出发 只要算法的DFS/BFS顺序不变 那么我们如何得到这个unique key的方法就有很多了...

import java.util.HashSet;
import java.util.Set;

/** WARNING: DO NOT FORGET to add path for backtracking, otherwise, we may have same result when we count two
 * distinct islands in some cases
 *
 * eg:              1 1 1   and    1 1 0
 *                  0 1 0          0 1 1
 * with b:          rdbr           rdr
 * without b:       rdr            rdr
 * */

/*
public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "o"); // origin
                    grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if(i < 0 || i == grid.length || j < 0 || j == grid[i].length
                || grid[i][j] == 0) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, "u");
        dfs(grid, i+1, j, sb, "d");
        dfs(grid, i, j-1, sb, "l");
        dfs(grid, i, j+1, sb, "r");
        sb.append("b"); // back
    }
}
 */


//犯了究极错误的代码 对于一个method 你传入一个object 传入的是什么是reference啊
    //比如这个题你s = "" 然后传入dfs()中 进去之后出来s还是指向"" 因为传进method的是一个reference的copy
    //因为String是immutable的你s = s + "U"这种操作知识让你在method中的reference指向了一个新的string
    //而外面真正的s 依然指向一个空的string.... immutable意味着 你每次改变string  其实都生成一个新的string
    //所以你这种写法犯了一个究极错误...
public class NumberOfDistinctIslands {
    private static final int[][] dirs = {{1,0},{0,-1},{-1,0},{0,1}};
    private static final String[] dirStr = {"R", "D", "L", "U"};
    public int numDistinctIslands(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String s = "";
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, s);
                    if (set.add(s)) {
                        res ++;
                    }
                }
            }
        }
        return res;
    }
    private void dfs(int[][] grid, int x, int y, String s) {
        grid[x][y] = 0;
        for (int i = 0; i < dirs.length; i++) {
            int row = dirs[i][0] + x;
            int col = dirs[i][1] + y;
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
                continue;
            }
            s = s + dirStr[i];
            dfs(grid, row, col, s);
        }
        //append # while return avoid corner case
        s = s + "#";
    }
}


/*
class Solution {

    int[][] dirs= new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    public int numDistinctIslands(int[][] grid) {
        Set<String> set= new HashSet<>();
        int res=0;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1) {
                    StringBuilder sb= new StringBuilder();
                    helper(grid,i,j,0,0, sb);
                    String s=sb.toString();
                    if(!set.contains(s)){
                        res++;
                        set.add(s);
                    }
                }
            }
        }
        return res;
    }

    public  void helper(int[][] grid,int i,int j, int xpos, int ypos,StringBuilder sb){
        grid[i][j]=0;
        sb.append(xpos+""+ypos);
        for(int[] dir : dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==0) continue;
            helper(grid,x,y,xpos+dir[0],ypos+dir[1],sb);
        }
    }
}
*/
