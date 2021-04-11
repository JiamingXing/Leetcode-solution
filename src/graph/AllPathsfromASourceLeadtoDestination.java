package graph;

import java.util.*;

//如果一个有向图 strongly connected -> 正反两遍做DFS 都能访问到所有的node
//这道题是一样的吗？ 其实不一样 毕竟这里规定了source和destination

//从source出发的所有路径 就表示我从source开始做 DFS 必须每一个DFS都return true才可以
//并且我们是允许访问重复node的
//但是不能在路径上不能出现环 所以我们想到了visited 回溯的方法

//0->1 1->1 要return false...

public class AllPathsfromASourceLeadtoDestination {
    enum Color {
        While,
        Grey,
        Black
    }
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge[1]);
        }
        Color[] colors = new Color[n];
        Arrays.fill(colors, Color.While);
        //boolean[] visited = new boolean[n];
        //忘了check destination是否有outdegree
        return dfs(graph, source, destination, colors) && !graph.containsKey(destination);
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, int cur, int dest, Color[] colors) {
        if (cur == dest) {
            return true;
        }
        //虽然我们去check了memorization
        //但是其实 只会从一个source 出发 你这个memorization没啥用 就是如果一条路径我们出现了这个grey
        //他必然是环 不可能是没有end at destiantion的node  因为如果碰到这样的路径 我们直接return false了
        //不过算法本身还是对的
        //时间上也差不多
        if (colors[cur] != Color.While) {
            return colors[cur] == Color.Black;
        }
        colors[cur] = Color.Grey;
        if (!graph.containsKey(cur)) {
            return false;
        }
        for (int neighbor : graph.get(cur)) {
            if (!dfs(graph, neighbor, dest, colors)) {
                return false;
            }
        }
        colors[cur] = Color.Black;
        return true;
    }
//    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//        for (int[] edge : edges) {
//            if (!graph.containsKey(edge[0])) {
//                graph.put(edge[0], new ArrayList<>());
//            }
//            graph.get(edge[0]).add(edge[1]);
//        }
//        boolean[] visited = new boolean[n];
//        //忘了check destination是否有outdegree
//        return dfs(graph, source, destination, visited) && !graph.containsKey(destination);
//    }
//    private boolean dfs(Map<Integer, List<Integer>> graph, int cur, int dest, boolean[] visited) {
//        if (cur == dest) {
//            return true;
//        }
//        if (visited[cur]) {
//            return false;
//        }
//        if (!graph.containsKey(cur)) {
//            return false;
//        }
//        visited[cur] = true;
//        for (int next : graph.get(cur)) {
//            if (!dfs(graph, next, dest, visited)) {
//                return false;
//            }
//        }
//        //忘了visited  回溯了
//        visited[cur] = false;
//        return true;
//    }
}
