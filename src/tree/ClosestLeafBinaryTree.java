package tree;

//这道题要求给你一个node的val 然后求最近的leaf的val是多少 这道题还是挺有意思的 之前没有做过这样的题
//一开始分析一个node如果要求最近的leaf 我们需要知道当前节点往下走到leaf的min depth,还要知道所有往上走的parent
//对于这种求最近的问题 我们其实都可以想到用BFS 因为每条edge都是相同的 唯一不同的是 我们需要找到node
//与其parent对应的edge的关系 所以我们需要先用DFS找到value = k的node以及 他们与parent之间的联系

//首先这道题不是自己做出来的 自己当初的思路走偏了 纯粹用DFS行不通 但是想清楚 这道题怎么让你能想到用BFS解决很关键

import java.util.*;

public class ClosestLeafBinaryTree {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode target = dfs(root, k, map);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.offer(target);
        while (!Q.isEmpty()) {
            TreeNode cur = Q.poll();
            if (cur.left == null && cur.right == null) {
                return cur.val;
            }
            if (cur.left != null && visited.add(cur.left)) {
                Q.offer(cur.left);
            }
            if (cur.right != null && visited.add(cur.right)) {
                Q.offer(cur.right);
            }
            if (map.containsKey(cur) && visited.add(map.get(cur))) {
                Q.offer(map.get(cur));
            }
        }
        return -1;
    }
    private TreeNode dfs(TreeNode root, int k, Map<TreeNode, TreeNode> map) {
        if (root.val == k) {
            return root;
        }
        if (root.left != null) {
            map.put(root.left, root);
            TreeNode left = dfs(root.left, k, map);
            if (left != null) {
                return left;
            }
        }
        if (root.right != null) {
            map.put(root.right, root);
            TreeNode right = dfs(root.right, k, map);
            if (right != null) {
                return right;
            }
        }
        return null;
    }
}
