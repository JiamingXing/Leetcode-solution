package com.jimmy.twopointers;

//复习的时候写了一个O(n^2)复杂度的算法 就是每次碰到0 我都往后找第一个非0数组交换位置
//有更好的办法

public class MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                int j = i+1;
                while (j < nums.length) {
                    if (nums[j] != 0) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        break;
                    }
                    j ++;
                }
            }
            i ++;
        }
        return;
    }
}



//思路不要局限于 移动0 而是能想到按照顺序插入非0数组
//这样值需要O(n)的时间复杂度即可
/*
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos ++] = nums[i];
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos ++] = 0;
        }
    }
}
*/


