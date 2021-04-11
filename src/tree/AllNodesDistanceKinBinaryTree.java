package tree;

//思路先遍历一遍tree 建立一个无向图 用map存储 然后从target出发做DFS找到所有的点

import java.util.*;

//DFS

public class AllNodesDistanceKinBinaryTree {
    public List<Integer> distanceK(TreeNode root, int target, int K) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        generateGraph(root, null, graph);
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(target);
        dfs(target, graph, res, 0, K, visited);
        return res;
    }
    private void generateGraph(TreeNode cur, TreeNode parent, Map<Integer, List<Integer>> graph) {
        graph.put(cur.val, new ArrayList<>());
        if (parent != null) {
            graph.get(cur.val).add(parent.val);
        }
        if (cur.left != null) {
            generateGraph(cur.left, cur, graph);
        }
        if (cur.right != null) {
            generateGraph(cur.right, cur, graph);
        }
    }
    private void dfs(int cur, Map<Integer, List<Integer>> graph, List<Integer> res, int dist, int K, Set<Integer> visited) {
        visited.add(cur);
        if (dist == K) {
            res.add(cur);
            return;
        }
        for (int next : graph.get(cur)) {
            if (!visited.contains(next)) {
                dfs(next, graph, res, dist+1, K, visited);
            }
        }
    }
}


///BFS
/*
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, parent);
        Queue<TreeNode> Q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        Q.offer(target);
        visited.add(target);
        while (!Q.isEmpty() || K >= 0) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = Q.poll();
                if (K == 0) {
                    res.add(cur.val);
                }
                if (cur.left != null && visited.add(cur.left)) {
                    Q.offer(cur.left);
                }
                if (cur.right != null && visited.add(cur.right)) {
                    Q.offer(cur.right);
                }
                if (parent.containsKey(cur) && visited.add(parent.get(cur))) {
                    Q.offer(parent.get(cur));
                }
            }
            K --;
        }
        return res;
    }
    private void helper(TreeNode root, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.put(root.left, root);
            helper(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right, root);
            helper(root.right, map);
        }
    }
}

 */
