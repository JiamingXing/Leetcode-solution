package dfs;

//这道题的要求也是非常直接 就是给你一串路径 先判断是否有环 再判断是不是fully connected
//BFS/DFS/UNION FIND都可以做

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


//DFS
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) {
            return false;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (!map.containsKey(x)) {
                map.put(x, new HashSet<>());
            }
            if (!map.containsKey(y)) {
                map.put(y, new HashSet<>());
            }
            map.get(x).add(y);
            map.get(y).add(x);
        }
        Set<Integer> visited = new HashSet<>();
        if (dfs(map, visited, 0)) {
            return false;
        }
        return visited.size() == n;
    }
    private boolean dfs(Map<Integer, Set<Integer>> map, Set<Integer> visited, int cur) {
        if (visited.contains(cur)) {
            return true;
        }
        visited.add(cur);
        if (!map.containsKey(cur) || map.get(cur).isEmpty()) {
            return false;
        }
        for (int next : map.get(cur)) {
            map.get(next).remove(cur);
            if (dfs(map, visited, next)) {
                return true;
            }
        }
        return false;
    }
}




//Union Find
/*
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) {
            return false;
        }
        if (edges.length == 0) {
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (find(parent, x) == find(parent, y)) {
                return false;
            }
            parent[find(parent, x)] = find(parent, y);
            n--;
        }
        return n == 1;
    }
    private int find(int[] parent, int id) {
        if (parent[id] == -1) {
            return id;
        }
        parent[id] = find(parent, parent[id]);
        return parent[id];
    }
}
*/
