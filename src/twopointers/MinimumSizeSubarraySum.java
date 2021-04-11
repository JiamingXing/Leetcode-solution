package twopointers;

//sliding window的思路来做 因为是和subarray/substring有关会想到用sliding window

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0, sum = 0, res = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right++];
            if (sum >= s) {
                while (sum >= s) {
                    res = Math.min(res, right - left);
                    sum -= nums[left ++];
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
