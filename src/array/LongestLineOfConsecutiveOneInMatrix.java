package array;

public class LongestLineOfConsecutiveOneInMatrix {
    //这个code就非常clean
    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0) return 0;
        int m = M.length, n = M[0].length;
        int max = 0, hori = 0, vert = 0, inc = 0, desc = 0;
        for (int i = 0; i < m; i++) {
            hori = 0;
            for (int j = 0; j < n; j++) {
                hori = M[i][j] > 0 ? hori + 1 : 0;
                max = Math.max(max, hori);
            }
        }
        for (int j = 0; j < n; j++) {
            vert = 0;
            for (int i = 0; i < m; i++) {
                vert = M[i][j] > 0 ? vert + 1 : 0;
                max = Math.max(max, vert);
            }
        }
        for (int k = 0; k < m + n; k++, inc = 0, desc = 0) {
            // increasing start from left cells then bottom cells
            for (int i = Math.min(k, m - 1), j = Math.max(0, k - m); i >= 0 && j < n; i--, j++) {
                inc = M[i][j] > 0 ? inc + 1 : 0;
                max = Math.max(max, inc);
            }
            // decreasing start from left cells then top cells;
            for (int i = Math.max(m - 1 - k, 0), j = Math.max(0, k - m); i < m && j < n; i++, j++) {
                desc = M[i][j] > 0 ? desc + 1 : 0;
                max = Math.max(max, desc);
            }
        }
        return max;
    }
}
