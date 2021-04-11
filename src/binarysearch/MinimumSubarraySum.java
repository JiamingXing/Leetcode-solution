package binarysearch;

public class MinimumSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right++];
            if (sum >= s) {
                while (sum >= s) {
                    res = Math.min(res, right - left);
                    sum -= nums[left++];
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
