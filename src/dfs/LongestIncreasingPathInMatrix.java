package dfs;

//Memorization
//一开始会想着去用 boolean[][] visited记录访问过的路径 但是这道题潜在条件是递增路径 所以我们只要判断满足条件的下个递增点
//就说明后面的肯定比当前的大 不可能会绕回来
public class LongestIncreasingPathInMatrix {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] count = new int[m][n];
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, helper(matrix, count, i, j));
            }
        }
        return res;
    }
    private int helper(int[][] matrix, int[][] count, int i, int j) {
        if (count[i][j] != 0) {
            return count[i][j];
        }
        int max = 1;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row < 0 || col < 0 || row >=matrix.length || col >= matrix[0].length
                    || matrix[row][col] <= matrix[i][j]) {
                continue;
            }
            max = Math.max(max, helper(matrix, count, row, col) + 1);
        }
        count[i][j] = max;
        return max;
    }
}
