package sort;

//O(n)时间复杂度 一开始想法是整个sort了 然后two pointer进行swap 这样时间复杂度是O(nlogn)

public class WiggleSort {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //if i%2 == 1 num[i] > nun[i-1]
            //if not swap i and i-1
            if (i % 2 == 1) {
                if (nums[i] < nums[i-1]) {
                    swap(nums, i);
                }
                //if i % 2 == 0 nums[i] < nums[i-1]
                //if not swap
            } else if (i != 0 && nums[i] > nums[i-1]) {
                swap(nums, i);
            }
        }
    }
    private void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i-1];
        nums[i-1] = temp;
    }
}
