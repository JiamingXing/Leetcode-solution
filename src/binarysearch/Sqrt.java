package binarysearch;

//如果按照找第一个x^2 >= x的可以
//如果按照找最后一个 x^2 <= x会死循环。。？

//注意如果是用mid * mid的话可能溢出 但是出发会向下取整 想起来麻烦一些

public class Sqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int start = 1;
        int end = x;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid >= x / mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (start == x / start) {
            return start;
        } else {
            return start - 1;
        }

        /*
        if (x == 0) {
            return 0;
        }
        int start = 1;
        int end = x;
        //find last mid <= x/mid
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (mid == x/mid) {
                // start = mid; end = mid //both OK
                return mid;
            } else if (mid < x/mid) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end <= x/end) {
            return end;
        } else {
            return start;
        }
         */
    }
}
