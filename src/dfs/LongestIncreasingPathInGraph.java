package dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    int val;
    public Node(int val) {
        this.val = val;
    }
}

//circle exists

public class LongestIncreasingPathInGraph {
    public int longestIncreasingPath(Map<Node, List<Node>> graph) {
        int res = 0;
        Map<Node, Integer> memorization = new HashMap<>();
        for (Node n : graph.keySet()) {
            //好像都可以 因为访问过的点 必然比之前的小吧
//            if (!memorization.containsKey(n)) {
//                res = Math.max(res, dfs(n, graph, memorization));
//            }
            res = Math.max(res, dfs(n, graph, memorization));
        }
        return res;
    }
    private int dfs(Node cur, Map<Node, List<Node>> graph, Map<Node, Integer> memorization) {
        if (memorization.containsKey(cur)) {
            return memorization.get(cur);
        }
//        if (!graph.containsKey(cur)) {
//            return 0;
//        }
        int res = 1;
        for (Node next : graph.get(cur)) {
            if (next.val > cur.val) {
                res = Math.max(res, dfs(next, graph, memorization)+1);
            }
        }
        memorization.put(cur, res);
        return res;
    }
}
