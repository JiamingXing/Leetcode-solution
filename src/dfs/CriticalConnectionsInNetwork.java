package dfs;

import java.util.ArrayList;
import java.util.List;

//给你一个图 找到所有的edge 这个edge如果断开 那么整个图无法fully connected
//拿到之后有点懵逼 那么我们如何找思路呢
//什么样的edge满足条件？首先如果某个edge是一个node的唯一edge 那么这个edge肯定满足条件
//其次环中的edge一定不满足条件 这是可以想到的两个点 然后我刚刚尝试了一下好像成环的edge以外的edge都是满足条件的edge？

//假如这个发现是题目的正确思路 那么问题转换成 在一个无向图中找出所有环 不是环中的edge都是答案
// 那是不是就是我们在找环的过程中如果发现DFS中无环
//就把路径上的edge加到我么你的结果中
//问题来了 因为是无向图 如何不走回头路？
//在写的过程中我 如果visited[node]表示出现环了 这个时候更好的办法是记录这个环的路径。。。
//所以node当前出现环的路径 -> 如何print？
//想到了一个思路就是我们做DFS的时候把edge都加到res中 然后发现node visited 出现环就从当前res的末尾移除到最近包含node

public class CriticalConnectionsInNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        boolean[] visited = new boolean[n];
        int[][] graph = new int[n][n];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            int n1 = connections.get(i).get(0);
            int n2 = connections.get(i).get(1);
            graph[n1][n2] = 1;
            graph[n2][n1] = 1;
        }
        dfs(graph, visited, res, 0, n);
        return res;
    }
    private void dfs(int[][] graph, boolean[] visited, List<List<Integer>> res, int cur, int n) {
        //这里remove的罗技出问题了。。。
        if (visited[cur]) {
            //remove circle
            res.remove(res.size()-1);
            int i = res.size()-1;
            //find last cur in res
            while (i >= 0 && res.get(i).get(0) != cur && res.get(i).get(1) != cur) {
                i --;
            }
            if (i != -1) {
                //remove from i to tail
                for (int j = 0; j < res.size()-i; j++) {
                    res.remove(res.size()-1);
                }
            }
            return;
//            while (!res.isEmpty() && (res.get(res.size()-1).get(0) == cur || res.get(res.size()-1).get(1) == cur)) {
//                res.remove(res.size()-1);
//            }
//            return;
        }
        visited[cur] = true;
        for (int i = 0; i < n; i++) {
            if (graph[cur][i] > 0) {
                graph[cur][i] = 0;
                graph[i][cur] = 0;
                //add current edge to res
                List<Integer> temp = new ArrayList<>();
                temp.add(cur);
                temp.add(i);
                res.add(temp);
                dfs(graph, visited, res, i, n);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        List<Integer> temp3 = new ArrayList<>();
        List<Integer> temp4 = new ArrayList<>();
        temp1.add(0);
        temp1.add(1);
        connections.add(temp1);
        temp2.add(1);
        temp2.add(2);
        connections.add(temp2);
        temp3.add(2);
        temp3.add(0);
        connections.add(temp3);
        temp4.add(1);
        temp4.add(3);
        connections.add(temp4);
        CriticalConnectionsInNetwork c = new CriticalConnectionsInNetwork();
        System.out.println(c.criticalConnections(4, connections));

    }
}

//正确的算法 而且有名字 Tarjan Algorithm
//你知道这个算法可以帮助你找到所有环中的edge或者非环中的edge
/*
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] disc = new int[n], low = new int[n];
        // use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
        List<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // build graph
        for (int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0), to = connections.get(i).get(1);
            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, low, disc, graph, res, i);
            }
        }
        return res;
    }

    int time = 0; // time when discover each vertex

    private void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
        disc[u] = low[u] = ++time; // discover u
        for (int j = 0; j < graph[u].size(); j++) {
            int v = graph[u].get(j);
            if (v == pre) {
                continue; // if parent vertex, ignore
            }
            if (disc[v] == -1) { // if not discovered
                dfs(v, low, disc, graph, res, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    res.add(Arrays.asList(u, v));
                }
            } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
*/