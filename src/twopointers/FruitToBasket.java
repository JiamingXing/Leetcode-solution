package twopointers;

//题目转换成maximum length of subarray containing at most 2 different numbers

import java.util.HashMap;
import java.util.Map;

public class FruitToBasket {
    public int totalFruit(int[] tree) {
        int left = 0, right = 0, res = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (right < tree.length) {
            int n = tree[right++];
            if (map.getOrDefault(n, 0) == 0) {
                count ++;
            }
            map.put(n, map.getOrDefault(n, 0) + 1);
            while (count > 2) {
                int m = tree[left++];
                if (map.get(m) == 1) {
                    count --;
                }
                map.put(m, map.get(m)-1);
            }
            res = Math.max(res, right-left);
        }
        return res;
    }
}
