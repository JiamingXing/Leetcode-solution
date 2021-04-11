package twopointers;

import java.util.Arrays;

//使用sort的话我们不需要额外的space可以做到O(nlogn)的时间复杂度
public class KDiffPairsInArray {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i + 1;
            while (j < nums.length) {
                if (nums[j] - nums[i] == k) {
                    res ++;
                    break;
                } else if (nums[j] - nums[i] < k) {
                    j ++;
                } else {
                    break;
                }
            }
        }
        return res;
    }
}

//使用一个hashmap我们可以做到O(n)的时间复杂度
/*
public class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)   return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }
}
*/
