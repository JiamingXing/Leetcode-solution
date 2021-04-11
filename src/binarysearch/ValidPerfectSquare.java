package binarysearch;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        while (start < end) {
            long mid = (long)(start + (end - start) / 2);
            if (mid * mid == num) {
                return true;
            }
            if (mid * mid > num) {
                end = (int)(mid - 1);
            } else {
                start = (int)(mid + 1);
            }
        }
        if (start * start == num) {
            return true;
        }
        return false;
    }
}
