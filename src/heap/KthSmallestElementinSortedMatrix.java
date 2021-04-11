package heap;

import java.util.PriorityQueue;

//用pq做会慢一些 时间复杂度是O(nlogn)
//可以用binary search 做

public class KthSmallestElementinSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        k = m*n - k + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.add(matrix[i][j]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
}



//思考下二分法的思路
/*
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int start = matrix[0][0];
        int end = matrix[m-1][n-1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            //number of elements smaller than mid
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
*/
