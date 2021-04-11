package binarysearch;

//这道题的思路关键在于 binary search + overflow case的细心处理
//最关键还是要想到用binary search
//如果要只用+ -来实现除法 那么最蠢的思路一定是一次次加 时间复杂度O(m/n) 我们就要优化到O(log(m/n))

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        //dividend = Integer.MIN   divisor = -1 -> overflow
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (dividend < divisor) {
            return 0;
        }
        //find last integer res -> res * ldivisor <= ldividend
        long start = 1;
        long end = ldividend;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * ldivisor <= ldividend) {
                start = mid;
            } else {
                end = mid;
            }
        }
        long res = 0;
        if (end * ldivisor <= ldividend) {
            res = end;
        } else {
            res = start;
        }
        if (res > Integer.MAX_VALUE && sign == -1) {
            return Integer.MAX_VALUE;
        } else {
            return sign * (int)res;
        }
        /*
        long temp = binarySearch(ldividend, ldivisor);
        if (temp > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return sign * (int)temp;
        }
         */
    }
    /*
    private long binarySearch(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor;
        long mul = 1;
        while (sum + sum <= dividend) {
            sum += sum;
            mul += mul;
        }
        return mul + binarySearch(dividend - sum, divisor);
    }
     */

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
