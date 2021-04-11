package tree;

import java.util.*;


public class BinaryTreeVerticalOrderTraversal {
    private class Pair {
        TreeNode node;
        int row;
        public Pair(TreeNode node, int row) {
            this.node = node;
            this.row = row;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair> Q = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        Q.offer(new Pair(root, 0));
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                Pair cur = Q.poll();
                if (cur.row < min) {
                    min = cur.row;
                    res.add(0, new ArrayList<>());
                    res.get(0).add(cur.node.val);
                } else {
                    int dif = cur.row - min;
                    if (dif + 1 > res.size()) {
                        res.add(new ArrayList<>());
                        res.get(res.size()-1).add(cur.node.val);
                    } else {
                        res.get(dif).add(cur.node.val);
                    }
                }
                if (cur.node.left != null) {
                    Q.offer(new Pair(cur.node.left, cur.row-1));
                }
                if (cur.node.right != null) {
                    Q.offer(new Pair(cur.node.right, cur.row+1));
                }
            }
        }
        return res;
    }
}

/*
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        int min = 0;
        int max = 0;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }
}

 */
