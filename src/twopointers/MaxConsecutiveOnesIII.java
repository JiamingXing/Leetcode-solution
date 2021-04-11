package twopointers;

public class MaxConsecutiveOnesIII {
    //maximum subarray contains at most K 0
    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int count = 0;
        int res = 0;
        while (right < A.length) {
            if (A[right++] == 0) {
                count ++;
            }
            while (count > K) {
                if (A[left++] == 0) {
                    count --;
                }
            }
            res = Math.max(res, right-left);
        }
        return res;
    }
}
