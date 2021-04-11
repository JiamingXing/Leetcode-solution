package twopointers;

//思路比较简单 从Array找第一个大于等于0的元素 然后对三种情况进行分类讨论 two pointer遍历
//想一想能不能把代码写的更好看一点

//其实这种思路是错误的。。思路不要局限在从小到大啊。。。因为不管负正 两头总有最大的那个...

//class Solution {
//    public int[] sortedSquares(int[] A) {
//        int n = A.length;
//        int[] result = new int[n];
//        int i = 0, j = n - 1;
//        for (int p = n - 1; p >= 0; p--) {
//            if (Math.abs(A[i]) > Math.abs(A[j])) {
//                result[p] = A[i] * A[i];
//                i++;
//            } else {
//                result[p] = A[j] * A[j];
//                j--;
//            }
//        }
//        return result;
//    }
//}

public class SquaresofSortedArray {
    public int[] sortedSquares(int[] A) {
        int[] pointers = binarySearch(A);
        return helper(A, pointers[0], pointers[1]);
    }
    private int[] helper(int[] A,int i, int j) {
        int[] res = new int[A.length];
        int ind = 0;
        if (i == j) {
            if (i == 0) {
                while (ind < A.length) {
                    res[ind++] = A[i] * A[i];
                    i ++;
                }
            } else if (i == A.length - 1) {
                while (ind < A.length) {
                    res[ind++] = A[i] * A[i];
                    i --;
                }
            }
        } else {
            while (ind < A.length) {
                int a = i == -1 ? Integer.MAX_VALUE: A[i] * A[i];
                int b = j == A.length ? Integer.MAX_VALUE : A[j] * A[j];
                if (a < b) {
                    res[ind] = a;
                    i --;
                } else {
                    res[ind] = b;
                    j ++;
                }
                ind ++;
            }
        }
        return res;
    }
    private int[] binarySearch(int[] A) {
        //find first element >= 0
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] >= 0) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int i, j = -1;
        if (A[start] >= 0) {
            i = start;
            j = start == 0 ? start : start-1;
        } else if (A[end] >= 0) {
            i = start;
            j = end;
        } else {
            i = end;
            j = end;
        }
        int[] res = new int[2];
        res[0] = i;
        res[1] = j;
        return res;
    }
}
