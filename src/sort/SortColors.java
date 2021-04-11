package sort;

//用partition的思路 two pointer进行sort 因为这里题目明确告诉你只有三种颜色 可以这么做

public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                swap(nums, i++, j);
            }
        }
        for (int j = i; j < nums.length; j++) {
            if (nums[j] == 1) {
                swap(nums, i++, j);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



//两根指针从头和尾分别向中间靠拢这样 会快一点
/*
public class Solution {
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            while (start <= end && nums[start] == 0) {
                start ++;
            }
            while (start <= end && nums[end] > 0) {
                end --;
            }
            if (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start ++;
                end --;
            }
        }
        end = nums.length - 1;
        while (start <= end) {
            while (start <= end && nums[start] == 1) {
                start ++;
            }
            while (start <= end && nums[end] > 1) {
                end --;
            }
            if (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start ++;
                end --;
            }
        }
    }
}
*/
