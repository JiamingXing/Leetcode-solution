package graph;

import java.util.*;

//给你一些edge 我可以根据这些edge用一个map建图 然后bfs+visited 来count
//也可以用union find来做 两个点如果本来就相连 继续 如果不想连 union一下 count-1

public class NumberOfConnectedinUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int res = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            res ++;
            Q.offer(i);
            while (!Q.isEmpty()) {
                int cur = Q.poll();
                for (int neighbor : map.get(cur)) {
                    if (!visited[neighbor]) {
                        Q.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
        }
        return res;
    }
}


//union find
/*
class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] father = new int[n];
        Arrays.fill(father, -1);

        for (int[] edge : edges) {
            int x = find(father, edge[0]);
            int y = find(father, edge[1]);

            if (x == y) {
                continue;
            }
            father[x] = y;
            n --;
        }
        return n;
    }
    private int find(int[] num, int id) {
        if (num[id] == -1) {
            return id;
        }
        return find(num, num[id]);
    }
}
*/
