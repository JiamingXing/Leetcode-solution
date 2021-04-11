package heap;

//这道题的关键在于 首先你要判断他能不能reorganize 如果一个字符的数量大于长度的一半 那肯定是不行的
//其次在于要有greedy的思想 就是数量多的字符先排 一个挨着一个
//这样我们能想到用pq 根据字符数量进行排序 并且粘贴

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorgnizeString {
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > (S.length() + 1) / 2) {
                return "";
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (char c : map.keySet()) {
            pq.add(new int[]{c, map.get(c)});
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (sb.length() == 0 || (char)cur[0] != sb.charAt(sb.length()-1)) {
                sb.append((char)cur[0]);
                if (--cur[1] > 0) {
                    pq.add(cur);
                }
            } else {
                int[] next = pq.poll();
                sb.append((char)next[0]);
                if (--next[1] > 0) {
                    pq.add(next);
                }
                pq.add(cur);
            }
        }
        return sb.toString();
    }
}
