package binarysearch;

//下面的代码是自己尝试不用九章算法的模板写 已经把自己完全写乱了 出现了死循环

public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int[] res = new int[2];
        //find first element >= target
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] != target) {
            return new int[]{-1, -1};
        } else {
            res[0] = start;
        }
        //find last element <= target
        start = 0;
        end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        res[1] = start;
        return res;
    }
}



//根据没过的case进行小的修改 通过 主要是找最后一个等于target的元素的时候
//这个<= target的情况 start = mid + 1 还是mid的问题
/*
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int[] res = new int[2];
        //find first element >= target
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] != target) {
            return new int[]{-1, -1};
        } else {
            res[0] = start;
        }
        //find last element <= target
        start = 0;
        end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (nums[start] == target) {
            res[1] = start;
        } else {
            res[1] = start - 1;
        }
        return res;
    }
}
*/