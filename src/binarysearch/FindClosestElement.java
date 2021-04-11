package binarysearch;

//有一个sorted array 然后给一个target 找最接近target的数的作坐标
//很明显用binary search做 但是数组允许有重复元素 如果答案不止一个 返回坐标最小的那个

//目前能考虑到的一些case：
//[1,3,3] t=3 left=0 end=1
//[3,3,3,3] t=3 left=0 right=1
//[1,3,3,5] t=4 left=1 right=2
//[1,3,6,7,8] t=5 left=1 right=2
//[3,3,3,3] t=4 left=2 right=3

//time complexity:
// worst case: O(n) average O(logn)

public class FindClosestElement {
    public int findClosestElement(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (Math.abs(nums[end]-target) < Math.abs(nums[start]-target)) {
            return end;
        } else {
            while (start > 0 && nums[start-1] == nums[start]) {
                start --;
            }
            return start;
        }
    }
}
