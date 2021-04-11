package com.jimmy.twopointers;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i+1;
            int k = nums.length -1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    j ++;
                } else if (sum > target) {
                    k --;
                }
                if (Math.abs(target - sum) < min) {
                    res = sum;
                    min = Math.abs(target - sum);
                }
            }
        }
        return res;
    }
}
