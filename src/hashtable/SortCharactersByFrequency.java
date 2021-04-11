package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//O(n) with bucket sort...

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            max = Math.max(max, map.get(c));
        }
        List<Character>[] bucket = new List[max + 1];
        // List<Character>[] bucket = new List<Character>[max + 1];
        for (char c : map.keySet()) {
            int cur = map.get(c);
            if (bucket[cur] == null) {
                bucket[cur] = new ArrayList<>();
            }
            bucket[cur].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max; i >= 1; i --) {
            if (bucket[i] != null) {
                List<Character> cur = bucket[i];
                for (char c : cur) {
                    int repeatTime = i;
                    while (repeatTime > 0) {
                        sb.append(c);
                        repeatTime --;
                    }
                }
            }
        }
        return sb.toString();
    }
}




//O(nlogm) with Priority Queue
/*
public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int)e.getValue(); i++)
                sb.append(e.getKey());
        }
        return sb.toString();
    }
}
*/