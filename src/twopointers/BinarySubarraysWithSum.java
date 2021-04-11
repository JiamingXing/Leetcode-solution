package twopointers;

//这个和subarray sum equals K的题是一模一样的
//只不过因为是binary的 所有subarray和在0-A.length之间所以我们可以用array代替一个hashmap仅此而已

//二刷的时候思路走到sliding window上去了...

public class BinarySubarraysWithSum {
    public int numSubarraysWithSum(int[] A, int S) {
        int[] count = new int[A.length+1];
        count[0] = 1;
        int cur = 0;
        int res = 0;
        for (int a : A) {
            cur += a;
            if (cur - S >= 0) {
                res += count[cur - S];
            }
            count[cur] ++;
        }
        return res;
    }
}
