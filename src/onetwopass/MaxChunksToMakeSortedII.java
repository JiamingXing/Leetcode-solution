package onetwopass;

public class MaxChunksToMakeSortedII {
    public static void main(String[] args) {
        int[] arr = {2,1,3,4,4};
        System.out.println(maxChunksToSorted(arr));
    }
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length, res = 0;
        int[] minArr = new int[n];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--) {
            min = Math.min(min, arr[i]);
            minArr[i] = min;
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (i+1 == n || max <= minArr[i+1]) {
                res ++;
                max = Integer.MIN_VALUE;
            }
        }
        return res;
    }
}
