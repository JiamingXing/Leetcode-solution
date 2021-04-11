package hashtable;

import java.util.HashMap;
import java.util.Map;

//这道题很精髓的一点是 map里存的是对应值最长的长度 而且我们只考虑没有在统计过的情况
//所以每次更新完之后我们只需要更新相应的头和尾的值 不需要考虑中间的map
//因为头尾更新说明中间全都在map中有相应的key 而两头以外并没有考虑过

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = map.containsKey(n-1) ? map.get(n-1) : 0;
                int right = map.containsKey(n+1) ? map.get(n+1) : 0;
                int sum = left + right + 1;
                res = Math.max(res, sum);
                map.put(n-left, sum);
                map.put(n+right, sum);
            }
        }
        return res;
    }
}


//Union-find 并查集的做法
/*
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        for (int n : nums) {
            parent.put(n, n);
            size.put(n, 1);
        }
        for (int n : nums) {
            if (parent.containsKey(n-1)) {
                union(parent, size, n-1, n);
            }
            if (parent.containsKey(n+1)) {
                union(parent, size, n, n+1);
            }
        }
        int res = 0;
        for (int key : size.keySet()) {
            res = Math.max(res, size.get(key));
        }
        return res;
    }
    public int find(Map<Integer, Integer> parent, int id) {
        if (parent.get(id) == id) {
            return id;
        }
        parent.put(id, find(parent, parent.get(id)));
        return parent.get(id);
    }
    public void union(Map<Integer, Integer> parent, Map<Integer, Integer> size, int a, int b) {
        int root_a = find(parent, a);
        int root_b = find(parent, b);
        if (root_a != root_b) {
            parent.put(root_a, root_b);
            size.put(root_b, size.get(root_b) + size.get(root_a));
        }
    }
}

 */