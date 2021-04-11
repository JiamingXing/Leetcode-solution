package dfs;

import java.util.*;

//要么DFS 从任意unvisited的点去做DFS 一个component中的点都会变成visited
//有环无所谓 有环就是DFS的时候碰到visited的点我们return就好了
//一次DFS就是一个component
//或者Union-find 因为给的edge是uniqe的不用管有向无向
//我们每一条edge就union(v,s)两个node 最后去check n个node的parent是不是自己
//是自己表示某个component的root
//或者一开始有n个component  每有两个union 就会减1 如果出现环就跳过
//也能得到答案

public class NumberofConnectedComponentsUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }
        int res = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            res ++;
            dfs(graph, i, visited);
        }
        return res;
    }
    private void dfs(Map<Integer, List<Integer>> graph, int cur, boolean[] visited) {
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }
            dfs(graph, next, visited);
        }
    }
}



//Union Find
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
        num[id] = find(num, num[id]);
        return num[id];
    }
}