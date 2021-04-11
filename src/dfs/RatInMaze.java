package dfs;

//matrix[i][j] > 0 means maximum steps a rat can jump from here..

public class RatInMaze {
    public boolean canRatReachDest(int[][] matrix) {
        return dfs(matrix, 0, 0);
    }
    private boolean dfs(int[][] matrix, int x, int y) {
        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            return true;
        }
        int maxStep = matrix[x][y];
        for (int i = 1; i <= maxStep; i++) {
            int row = x+i;
            int col = y;
            if (row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0) {
                continue;
            }
            if (dfs(matrix, row, col)) {
                return true;
            }
        }
        for (int i = 1; i <= maxStep; i++) {
            int row = x;
            int col = y+i;
            if (row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0) {
                continue;
            }
            if (dfs(matrix, row, col)) {
                return true;
            }
        }
        return false;
    }
}
