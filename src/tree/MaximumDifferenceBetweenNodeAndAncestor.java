package tree;

//二刷写的从下到上
public class MaximumDifferenceBetweenNodeAndAncestor {
    class Wrapper {
        int max;
        int min;
        public Wrapper(int min, int max) {
            this.max = max;
            this.min = min;
        }
    }
    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return res;
    }
    private Wrapper dfs(TreeNode root) {
        if (root == null) {
            return new Wrapper(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Wrapper left = dfs(root.left);
        Wrapper right = dfs(root.right);
        int curMin = Math.min(left.min, right.min);
        int curMax = Math.max(left.max, right.max);
        if (root.left != null || root.right != null) {
            res = Math.max(res, Math.abs(root.val-curMin));
            res = Math.max(res, Math.abs(root.val-curMax));
        }
        return new Wrapper(Math.min(root.val, curMin), Math.max(root.val, curMax));
    }
}

//之前写的从上到下
/*
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return res;
        }
        helper(root.left, root.val, root.val);
        helper(root.right, root.val, root.val);
        return res;
    }
    private void helper(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }
        int curMax = Math.max(Math.abs(max-root.val),Math.abs(min-root.val));
        res = Math.max(res, curMax);
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        helper(root.left, min, max);
        helper(root.right, min, max);
    }
}

 */
