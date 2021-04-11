package twopointers;

//第一遍写的时候在移动left的时候 没考虑到left < right的情况
//之前做sliding window的题目都是字符串有关操作 好像都不需要考虑这个
//但是product = 0的话就要考虑 [1,2,3] k=0 如果没有left < right
//left会一直++ 直到超出index

//corner case在第二遍做的时候还是没考虑到

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int res = 0;
        int product = 1;
        while (right < nums.length) {
            product *= nums[right++];
            if (product < k) {
                res += (right - left);
            } else {
                while (product >= k && left < right) {
                    product = product / nums[left++];
                }
                res += (right - left);
            }
        }
        return res;
    }
}

/*
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int res = 0;
        int prod = 1;
        while (right < nums.length) {
            prod *= nums[right++];
            while (prod >= k && left < right) {
                prod /= nums[left++];
            }
            res += (right-left);
        }
        return res;
    }
    */
