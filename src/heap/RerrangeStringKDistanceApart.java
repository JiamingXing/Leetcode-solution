package heap;

import java.util.*;

public class RerrangeStringKDistanceApart {
    class Wrapper {
        public char c;
        public int count;
        public Wrapper(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        PriorityQueue<Wrapper> pq = new PriorityQueue<>((a,b) -> b.count-a.count);
        Queue<Wrapper> waitQ = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char key : map.keySet()) {
            pq.add(new Wrapper(key, map.get(key)));
        }
        while (!pq.isEmpty()) {
            Wrapper w = pq.poll();
            sb.append(w.c);
            w.count = w.count-1;
            waitQ.add(w);
            if (waitQ.size() < k) {
                continue;
            }
            Wrapper wrap = waitQ.poll();
            if (wrap.count > 0) {
                pq.add(wrap);
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
