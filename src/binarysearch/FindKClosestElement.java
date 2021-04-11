package binarysearch;

import java.util.ArrayList;
import java.util.List;

//写了好几遍才过 太粗心了...这样真的不行 面试的时候没有机会给你随便run 随便测试...

public class FindKClosestElement {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        //find first greater than or equal to x element in arr
        //two pointer
        int start = 0;
        int end = arr.length-1;
        while (start+1<end) {
            int mid = start+(end-start)/2;
            if (arr[mid] >= x) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int pos = -1;
        if (arr[start] >= x) {
            pos = start;
        } else {
            pos = end;
        }
        int left = pos-1;
        int right = pos;
        while (k > 0) {
            int leftDif = left >= 0 ? Math.abs(x-arr[left]) : Integer.MAX_VALUE;
            int rightDif = right < arr.length?  Math.abs(arr[right]-x) : Integer.MAX_VALUE;
            if (leftDif <= rightDif) {
                left = left < 0 ? left : left-1;
            } else {
                right = right >= arr.length ? right : right+1;
            }
            k--;
        }
        for (int i = left+1; i < right; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,1,1,2,3,6,7,8,9};
        FindKClosestElement f = new FindKClosestElement();
        f.findClosestElements(arr,9,4);
    }
}



//直接把找K长度的区间放在 binary search里面... 才是最好的写法
/*
public List<Integer> findClosestElements(int[] A, int k, int x) {
    int left = 0, right = A.length - k;
    while (left < right) {
        int mid = (left + right) / 2;
        if (x - A[mid] > A[mid + k] - x)
            left = mid + 1;
        else
            right = mid;
    }
    return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
}
 */