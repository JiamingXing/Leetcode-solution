package twopointers;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        while (i < A.length && j < B.length) {
            int[] a = A[i];
            int[] b = B[j];
            //no intersection move smaller one
            if (a[0] > b[1] || b[0] > a[1]) {
                if (a[0] > b[1]) {
                    j ++;
                } else {
                    i ++;
                }
            } else {
                //get intersection
                list.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
                if (a[1] < b[1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        int[][] res = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
