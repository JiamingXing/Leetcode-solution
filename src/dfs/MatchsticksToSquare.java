package dfs;

//如果用完所有的火柴 能否组成一个正方形 也就是4个sum/4

//其实就是暴力解 每个数都在四条边放一下 时间复杂度4^n 如果去想更巧妙的办法可能就跪了..

public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 4 != 0) {
            return false;
        }
        return helper(nums, 0, new int[4], sum/4);
    }
    private boolean helper(int[] nums, int pos, int[] sums, int target) {
        if (pos == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[pos] > target) {
                continue;
            }
            sums[i] += nums[pos];
            if (helper(nums, pos+1, sums, target)) {
                return true;
            }
            sums[i] -= nums[pos];
        }
        return false;
    }
}
