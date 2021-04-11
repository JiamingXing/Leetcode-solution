package hashtable;

//第二遍做的时候想到了sliding window....
//但是其实用preSum 更简单
//当前sum - S 如果大于0的话 我们就去map里找 这个频率 表示多少个subarray 到当前位置count = S的
//又因为我们知道A的长度所以我们不需要map 直接用array就可以解决

public class BianrySubarrayWithSum {
    public int numSubarraysWithSum(int[] A, int S) {
        return numSubarraysWithAtMostSum(A, S) - numSubarraysWithAtMostSum(A, S-1);
    }
    private int numSubarraysWithAtMostSum(int[] A, int S) {
        int left = 0, right = 0, count = 0, res = 0;
        while (right < A.length) {
            if (A[right++] == 1) {
                count ++;
            }
            while (count > S && left < right) {
                if (A[left++] == 1) {
                    count --;
                }
            }
            res += (right-left);
        }
        return res;
    }
}
