package com.jimmy.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//复习的时候明显很懈怠这道题 没好好看题目要跳过重复元素，所以在i,j,k这三个地方需要跳过重复元素都没有做
//而且竟然把temp 写在每一个i后面 想递归着做....

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i ++) {
            //跳过重复元素 避免重复triple出现在结果里
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i+1;
            int k = nums.length -1;
            while (j < k) {
                int sum = nums[i] + nums[k] + nums[i];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    res.add(temp);
                    //在相等的情况下 我们跳过j以及k对应的重复元素 避免相同的triple出现在结果里
                    while (j < k && nums[j+1] == nums[j]) {
                        j++;
                    }
                    while (k > j && nums[k-1] == nums[k]) {
                        k--;
                    }
                    j ++;
                    k --;
                } else if (sum > 0) {
                    k --;
                } else {
                    j ++;
                }
            }
        }
        return res;
    }
}
