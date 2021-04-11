package twopointers;

import java.util.ArrayList;
import java.util.List;

public class IntersectionofThreeSortedArray {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr3[k]) {
                j++;
            } else k++;
            //另一种写法
//            int min = Math.min(arr1[a],Math.min(arr2[b],arr3[c]));
//            if(arr1[a] == min && arr2[b] == min && arr3[c] == min) res.add(min);
//            if(arr1[a] == min) a++;
//            if(arr2[b] == min) b++;
//            if(arr3[c] == min) c++;
        }
        return result;
    }
}
