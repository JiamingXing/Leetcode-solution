package bfs;

//做这道题又犯了老错误。。总想着用很高端的解法 比如先计算以最佳的方式不考虑deadlock的问题需要几步
//然后再考虑中间死锁的情况。。但是程序是计算机执行的东西 有的时候 就bruteforce就好了
//就是从最原始的状态不断进行BFS 直到我们找到target为止

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpentheLock {
    public int openLock(String[] deadends, String target) {
        Queue<String> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>();
        for (String deadend : deadends) {
            deads.add(deadend);
        }
        Q.offer("0000");
        visited.add("0000");
        int res = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                String cur = Q.poll();
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return res;
                }
                for (int j = 0; j < 4; j++) {
                    char c = cur.charAt(i);
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    sb1.append(cur.substring(0,i)).append(c == '9' ? '0' : c+1).append(cur.substring(i+1));
                    sb2.append(cur.substring(0,i)).append(c == '0' ? '9' : c-1).append(cur.substring(i+1));
                    String s1 = sb1.toString();
                    String s2 = sb2.toString();
                    if (!visited.contains(s1) && deads.contains(s1)) {
                        Q.offer(s1);
                        visited.add(s1);
                    }
                    if (!visited.contains(s2) && deads.contains(s2)) {
                        Q.offer(s2);
                        visited.add(s2);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
