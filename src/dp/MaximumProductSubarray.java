package dp;

//可能会想到的一些问题 有0的时候 负数的时候 以及一直相乘的话会不会溢出啊？
//这道题感觉没做出来呀
//不要拘泥于 maximum subarray sum的思路
//curSum - prevSum
//我们记录当前的最大最小 然后乘以现在位置的值 来计算会比除法好很多啊
//因为除法毕竟会出现分母为0的case
//因为product 有一个潜在条件 你不断相乘结果只会越来越大。。只有正负号matter

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
//        int curProd = 1, min = 1, max = 1;
//        int res = Integer.MIN_VALUE;
//        for (int n : nums) {
//            curProd *= n;
//            //出现了0怎么办？
//            res = Math.max(res, Math.max(res/min, res/max));
//
//        }
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(nums[i], Math.max(nums[i]*temp, nums[i]*min));
            min = Math.min(nums[i], Math.min(nums[i]*temp, nums[i]*min));
            res = Math.max(res, max);
        }
        return res;
    }
}
