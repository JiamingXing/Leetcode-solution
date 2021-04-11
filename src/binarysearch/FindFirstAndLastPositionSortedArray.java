package binarysearch;

public class FindFirstAndLastPositionSortedArray {
    //放弃九章的思路 自己写binary search 每次binary search的时候 你在小于 等于 大于的时候分析清楚
    //归为2种可能 然后注意
    //你归类之后出现start = mid的时候 你就可能出现死循环了 因为mid永远落在左边
    public int[] searchRange(int[] nums, int target) {
        //first position = first element >= target
        //last position = last element <= target
        int[] res = new int[]{-1,-1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length, start = 0, end = n-1;
        //first element
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        if (nums[start] != target) {
            return res;
        }
        res[0] = start;
        start = 0;
        end = n-1;
        while (start < end) {
            int mid = start + (end-start) / 2 + 1;
            //想一想这里start = end
            //如果是3 ,4  mid=start 会造成死循环
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid-1;
            }
        }
        res[1] = start;
        return res;
    }
}
