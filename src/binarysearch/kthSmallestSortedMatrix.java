package binarysearch;

//我们不用heap 考虑二分法的做法

public class kthSmallestSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int start = matrix[0][0];
        int end = matrix[m-1][n-1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            int j = n - 1;
            for (int i = 0; i < m; i ++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j --;
                }
                count += (j + 1);
            }
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
