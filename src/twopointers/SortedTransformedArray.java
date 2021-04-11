package twopointers;

//要求在O(n)的时间进行排序
//容易形成的错觉是 你要在计算二次方程的时候做文章 但是其实计算二次方程就是O(1)的时间 主要是怎么利用O(n)的时间进行结果排序
//那么就要利用二次方程的特性 以及本身数组就是sort的
//如果a >=0 我们可以先确定最大的在头还是尾 所以我们可以从结果的尾开始insertion sort
//如果a < 0我们可以先确定最小的是在头还是尾

//核心在于：二次函数的特性 根据a的符号 头和尾必然是最大或最小 用两根指针将头和尾分别向中间移动进行排序

public class SortedTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int i = a >= 0 ? nums.length - 1 : 0;
        while (left <= right) {
            int start = compute(nums[left], a, b, c);
            int end = compute(nums[right], a, b, c);
            if (a >= 0) {
                if (start >= end) {
                    nums[i--] = start;
                    left ++;
                } else {
                    nums[i--] = end;
                    right --;
                }
            } else {
                if (start <= end) {
                    nums[i++] = start;
                    left ++;
                } else {
                    nums[i++] = end;
                    right --;
                }
            }
        }
        return res;
    }
    private int compute(int x, int a, int b, int c) {
        return a*x*x + b*x + c;
    }
}
