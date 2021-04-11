package dfs;

//图上的DFS题 destination node没有任何outgoing edges
//这道题的思路在哪里？ 不是很有思路 但是好像和union find有关？ 因为是否存在edge 从某个点到某个点不就是
//find(n1, n2) 或者从souce开始DFS 每个条outgoing的edge都走一下 如果能找到destination就是true？
//因为是有向图 我们不需要visited记录访问过的node 但是如果有环..不记录访问过的node 会出现无限循环的情况
//所以这个问题是不是就转换成从source出发的所有可能路径都能通过不重复路径找到destination

//写的好像想到了 是不是可以用个memorization  比如一个canReach[]表示n可以不可以所有path reach destination
//这样比如1

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPathsfromSourceLeadtoDestination {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge[1]);
        }
        boolean[] visited = new boolean[n];
        return dfs(graph,  visited, source, destination) && !graph.containsKey(destination);
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int source, int destination) {
        if (source == destination) {
            return true;
        }
        //有环
        if (visited[source]) {
            return false;
        }
        visited[source] = true;
        if (!graph.containsKey(source)) {
            return false;
        }
        for (int next : graph.get(source)) {
            if (!dfs(graph, visited, source, destination)) {
                return false;
            }
        }
        visited[source] = false;
        return true;
    }
}
