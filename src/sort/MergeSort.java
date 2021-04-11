package sort;

//Merge sort时间复杂度为O(nlogn)   not in-place
//

public class MergeSort {
    public void mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length - 1;
        sort(nums, 0, n);
    }
    private void sort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid+1, end);
        merge(nums, start, mid, end);
    }
    //merge的过程中需要用到extra space 所以不是sort in place的
    private void merge(int[] nums, int start, int mid, int end) {
        int[] left = new int[mid-start+1];
        int[] right = new int[end - mid];
        int index = 0;
        for (int i = start; i <= mid; i++) {
            left[index++] = nums[i];
        }
        index = 0;
        for (int i = mid+1; i <= end; i++) {
            right[index++] = nums[i];
        }
        int i = 0;
        int j = 0;
        index = start;
        while (index <= end && i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[index++] = left[i++];
            } else {
                nums[index++] = right[j++];
            }
        }
        while (i < left.length) {
            nums[index++] = left[i++];
        }
        while (j < right.length) {
            nums[index++] = right[j++];
        }
    }
}
