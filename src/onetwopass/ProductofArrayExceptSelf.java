package onetwopass;

//自己写的时候为啥非要用好几个array left, right, res...其实只要一个就可以了 不要用多余的space啊

public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[2];
        }
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        int temp = nums[n-1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i] * temp;
            temp *= nums[i];
        }
        return res;
    }
}