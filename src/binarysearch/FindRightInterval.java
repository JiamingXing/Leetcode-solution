package binarysearch;

//brute force的话 对于每个interval 我们遍历一遍 整个数组可以得到结果 O(n^2)时间复杂度
//自己能想到的思路是 ->
//我们可以sort 这个array 根据start point进行sort 时间是O(nlogn)
//sort完之后我们可以遍历一下 每个interval 我们都知道他的end 然后通过binary search找first index >= curEnd n*O(logn)
//同时我们因为需要原来的index...所以我们可以提前用一个Map处理一下 map的key可以是start(条件是每个start不同) 也可以根据start以及end
//变成一个unique string作为key来存储
//既然要sort了 可以用TreeMap 简化

//感觉这种类型的题 比较直白 然后

import java.util.*;

public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null) {
            return new int[0];
        }
        int n = intervals.length;
        int[] res = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }
        //这里就不要写老土的comparator override了... 学一下lambda expression....简洁明了
        /*
        Interval[] nints = Arrays.copyOf(ints, n);
        Arrays.sort(nints, Comparator.comparingInt(i -> i.start));
         */
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            int pos = binarySearch(intervals, curEnd);
            if (pos == -1) {
                res[map.get(curStart)] = -1;
            } else {
                res[map.get(curStart)] = map.get(intervals[pos][0]);
            }
        }
        return res;
    }
    private int binarySearch(int[][] intervals, int target) {
        int start = 0;
        int end = intervals.length-1;
        while (start+1<end) {
            int mid = start+(end-start)/2;
            if (intervals[mid][0] > target) {
                end = mid;
            } else if (intervals[mid][0] < target) {
                start = mid;
            } else {
                return mid;
            }
        }
        if (intervals[start][0] >= target) {
            return start;
        } else if (intervals[end][0] >= target){
            return end;
        }
        return -1;
    }
}

/*
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] result = new int[intervals.length];
        java.util.NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();

        for (int i = 0; i < intervals.length; ++i) {
            intervalMap.put(intervals[i].start, i);
        }

        for (int i = 0; i < intervals.length; ++i) {
            Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i].end);
            result[i] = (entry != null) ? entry.getValue() : -1;
        }

        return result;
    }
}
 */
