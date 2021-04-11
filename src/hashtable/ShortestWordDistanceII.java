package hashtable;

//given a list of words
//word1 != word2
//methods maybe called many times

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {

    Map<String, List<Integer>> map = new HashMap<>();
    public ShortestWordDistanceII(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<>());
            }
            map.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        //two pointer 的基本运用 为了求最小的距离
        //因为这两个list是sorted的 我们不需要逐一比较
        //可以每次移动较大的指针 从O(n^2) 优化到 O(n)
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                res = Math.min(res, list2.get(j) - list1.get(i));
                i ++;
            } else {
                res = Math.min(res, list1.get(i) - list2.get(j));
                j ++;
            }
        }
        return res;
    }
}
