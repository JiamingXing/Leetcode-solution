package tree;

import java.util.*;

//方向走偏了...复杂化了把这道题
//Quite similar problem as this one 968.Binary Tree Cameras.
//        So I put this as the first solution.
//        Write a dfs helper, return the number of coins given to the parent.

public class DistributeCoinsinBinaryTree {
    //TreeNode max = null;
    public int distributeCoins(TreeNode root) {
        //find the closest node with value > 1 to all node with value = 0
        //把树看成图 然后从最大值得点出发做BFS
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Queue<TreeNode> Q = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        //TreeNode max = null;
        TreeNode max = dfs(root, null, graph, Q, set);
        int res = 0;
        //int count = max.val;
        int level = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            level ++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = Q.poll();
                if (graph.containsKey(cur)) {
                    for (TreeNode iter : graph.get(cur)) {
                        if (set.add(iter)) {
                            if (iter.val == 0) {
                                res += level;
                                //count --;
                            }
                            Q.offer(iter);
                            // else if (iter.val > 1) {
                            //     count += (iter.val - 1);
                            // }
                        }
                    }
                }
            }
        }
        return res;
    }
    private TreeNode dfs(TreeNode root, TreeNode parent, Map<TreeNode, List<TreeNode>> map, Queue<TreeNode> Q, Set<TreeNode> set) {
         if (root == null) {
             return null;
         }
//        if (max == null || max.val < root.val) {
//            max = root;
//        }
        if (root.val > 1) {
             Q.offer(root);
             set.add(root);
        }
        map.put(root, new ArrayList<>());
        if (parent != null) {
            map.get(root).add(parent);
        }

        if (root.left != null) {
            map.get(root).add(root.left);
        }
        if (root.right != null) {
            map.get(root).add(root.right);
        }
        TreeNode left = dfs(root.left, root, map, Q, set);
        TreeNode right = dfs(root.right, root, map, Q, set);
        return maxNode(root, left, right);
    }
    private TreeNode maxNode(TreeNode root, TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return root;
        }
        if (left == null || right == null) {
            if (left == null) {
                return root.val > right.val ? root : right;
            }
            if (right == null) {
                return root.val > left.val ? root : left;
            }
        }
        TreeNode temp = left.val > right.val ? left : right;
        return root.val > temp.val ? root : temp;
    }
}


/*
    int res = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left), right = dfs(root.right);
        res += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
    */
