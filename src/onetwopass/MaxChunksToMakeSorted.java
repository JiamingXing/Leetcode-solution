package onetwopass;

public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        int max = -1;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                res ++;
                max = i < arr.length - 1 ? arr[i+1] : max;
            }
        }
        return res;
    }
}
