package twopointers;

//很好的一道题

//要求不能改变原数组
//O(1)额外空间
//时间复杂度小于O(n^2)
//只有一个重复的数组 但是可能出现很多次 1-n

//怎么想到用二分法解决？

public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1; // end = n
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int n : nums) {
                if (n <= mid && n >= start) {
                    count ++;
                }
            }
            if (count >= mid - start + 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}


//对于mid的处理 +1 -1总是搞不清楚。。。。。想想办法 把二分法彻底搞明白 统一有个模板才行
/*
class Solution {
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1; // end = n
        while (start <= end) {
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
                end = mid - 1;
            }
        }
        return start;
    }
}
*/
