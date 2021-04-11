package twopointers;

import java.util.*;

//follow up:
//What if elements of nums2 are stored in disk, and the memory is limited such that u can not load all elements into
//the memory at once?

//if nums2 cannot fit in memory, we put nums1 into hashmap, read chunks of nums2 and record intersection
//if both num1 and nums2 are very large and cannot fit in memory, sort them individually(external sort)
//then read 2 elements from each array  at a time into memory

//another follow up: if num1 size is small compared to nums2, you can use binary search to get
//time complexity O(mlogn) m << n

public class IntersectionsofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i ++;
            } else if (nums1[i] > nums2[j]) {
                j ++;
            } else {
                list.add(nums1[i]);
                i ++;
                j ++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}



//用两个map也可以搞定 只是需要一个额外的map的空间 时间复杂度都是O(m+n)
/*
public class IntersectionsofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
         Map<Integer, Integer> map = new HashMap<>();
         ArrayList<Integer> temp = new ArrayList<>();
         for (int n : nums1) {
         	map.put(n, map.getOrDefault(n, 0) + 1);
         }
         for (int n : nums2) {
         	if (map.containsKey(n) && map.get(n) > 0) {
         		temp.add(n);
         		map.put(n, map.get(n) - 1);
         	}
         }
         int[] res = new int[temp.size()];
         for (int i = 0; i < res.length; i++) {
         	res[i] = temp.get(i);
         }
         return res;
    }
}
*/
