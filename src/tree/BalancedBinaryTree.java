package tree;

/*
    LeetCode 110. Balanced Binary Tree
 */

public class BalancedBinaryTree {
    private class ResultType {
        int dep;
        boolean isBalanced;
        ResultType(int dep, boolean isBalanced) {
            this.dep = dep;
            this.isBalanced = isBalanced;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return helper(root).isBalanced;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (left.isBalanced && right.isBalanced && Math.abs(left.dep - right.dep) <= 1) {
            return new ResultType(Math.max(left.dep, right.dep) + 1, true);
        } else {
            return new ResultType(0, false);
        }
    }
}
