package binarysearch;

//这道题其实没办法用二分法做 worst case就必须把整个数组遍历一遍...所以我们需要证明这道题必须O(n)

public class SearchRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //用||是因为有重复的情况如果nums[mid] = nums[start] 还可以去判断比nums[end]大
            if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target >= nums[start] && nums[mid] > target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
                //如果nums[mid]和start end都相等而且都不是我们要找的 我们就舍去end
            } else {
                end --;
            }
        }
        if (nums[start] == target) {
            return true;
        } else if (nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }
}
