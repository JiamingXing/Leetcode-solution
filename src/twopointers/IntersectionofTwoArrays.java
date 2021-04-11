package twopointers;

import java.util.*;

//看看自己写的two pointer是啥东西 你非要跳过重复吗....

public class IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> temp = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                temp.add(nums1[i]);
                while (i+1 < nums1.length && nums1[i] == nums1[i+1]) {
                    i ++;
                }
                while (j+1 < nums2.length && nums2[j] == nums2[j+1]) {
                    j ++;
                }
                i ++;
                j ++;
            } else if (nums1[i] < nums2[j]) {
                while (i+1 < nums1.length && nums1[i] == nums1[i+1]) {
                    i ++;
                }
                i ++;
            } else {
                while (j+1 < nums2.length && nums2[j] == nums2[j+1]) {
                    j ++;
                }
                j ++;
            }
        }
        int[] res = new int[temp.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = temp.get(k);
        }
        return res;
    }
}


//看看别人写的two pointer....
/*
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
}
 */



//binary search做法
/*
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    private boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return true;
        } else if (nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }
}
*/


//two hashset
/*
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }
}
*/