package binarysearch;

//find the first element whose value is smaller than or equal to nums[0]

public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int n = nums.length;
        int target = nums[n-1];
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] <= target) {
            return nums[start];
        } else {
            return nums[start + 1];
        }
    }
}
