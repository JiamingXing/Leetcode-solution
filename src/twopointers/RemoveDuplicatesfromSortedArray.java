package com.jimmy.twopointers;

//这道题和remove zeros是一个思路 我们都用一个插入位置来不断更新数组的值

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            nums[insertPos++] = nums[i];
        }
        return insertPos;
    }
}
