package binarysearch;

//用count做二分法

public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int n : nums) {
                if (n <= mid) {
                    count ++;
                }
            }
            if (count <= mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
