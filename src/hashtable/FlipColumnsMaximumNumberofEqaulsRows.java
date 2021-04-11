package hashtable;

//flip cplumn -> equal row

//这道题如果能够转换成 find the i-th row, which has the most same or totally different rows in the matrix

import java.util.Arrays;

public class FlipColumnsMaximumNumberofEqaulsRows {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] flip = new int[n];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                flip[j] = 1 - matrix[i][j];
            }
            for (int k = 0; k < m; k++) {
                if (Arrays.equals(matrix[k], matrix[i]) || Arrays.equals(matrix[k], flip)) {
                    count++;
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
