package twopointers;

public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int res = 0;
        int count = 0;
        while (right < nums.length) {
            if (nums[right++] == 0) {
                count ++;
            }
            if (count > 1) {
                while (count > 1) {
                    if (nums[left++] == 0) {
                        count --;
                    }
                }
            }
            res = Math.max(res, right-left);
        }
        return res;
    }
}
