package binarysearch;

//画图分析 分类讨论
//先根据mid和target的比较 然后mid可能落在两种不同的区间 分析不同区间 舍弃半边的可能性 可以归为两种情况

public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length-1;
        while (start+1<end) {
            int mid = start + (end-start)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (nums[mid] > nums[end] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                if (nums[mid] < nums[end] && target > nums[end]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
