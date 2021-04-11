package amazon;

//最暴力是每个都配对一下 n^2
//如果两个sort 用two pointer nlogn + n^2?
//sort一个数组 每次fix另外一个数组的一个元素 然后在sorted array中找maximum number <= target

//听说如果sort 用two pointer会有三个case过不了？暴力解可以过

//如果是小于等于target呢？如果有多个解呢要求返回所有可能放在一个list中？

import java.util.Arrays;

public class ClosestTwoSum {
    public int closestSum(int target,int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = nums2.length-1;
        while (i < nums1.length && j >= 0) {
            int sum = nums1[i] + nums2[j];
            if (sum >= target) {
                j --;
            } else {
                if (target - sum < min) {
                    min = target-sum;
                }
                i ++;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;

//        List<List<Integer>> res = new ArrayList<>();
//        if (maxTravelDist < 0) {
//            return res;
//        }
//        Collections.sort(forwardRouteList, (a, b)->(a.get(1) - b.get(1)));
//        Collections.sort(returnRouteList, (a,b)->(a.get(1) - b.get(1)));
//        int m = forwardRouteList.size();
//        int n = returnRouteList.size();
//        int i = 0;
//        int j = n-1;
//        int min = Integer.MAX_VALUE;
//        while (i < m && j >= 0) {
//            int sum = forwardRouteList.get(i).get(1) + returnRouteList.get(j).get(1);
//            if (sum > maxTravelDist) {
//                j --;
//            } else {
//                if (maxTravelDist - sum <= min) {
//                    if (maxTravelDist - sum < min) {
//                        res.clear();
//                        min  = maxTravelDist - sum;
//                    }
//                    int iter1 = i+1;
//                    while (iter1 < m && forwardRouteList.get(iter1).get(1) == forwardRouteList.get(iter1-1).get(1)) {
//                        iter1++;
//                    }
//                    int iter2 = j-1;
//                    while (iter2 >= 0 && returnRouteList.get(iter2).get(1) == returnRouteList.get(iter2+1).get(1)) {
//                        iter2 --;
//                    }
//                    for (int left = i; left < iter1; left++) {
//                        for (int right = j; right > iter2; right--) {
//                            List<Integer> temp = new ArrayList();
//                            temp.add(forwardRouteList.get(left).get(0));
//                            temp.add(returnRouteList.get(right).get(0));
//                            res.add(temp);
//                        }
//                    }
//                    i = iter1;
//                    j = iter2;
//                } else {
//                    i ++;
//                }
//            }
//        }
//        return res;
    }
}
