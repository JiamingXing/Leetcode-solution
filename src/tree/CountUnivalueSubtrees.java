package tree;

public class CountUnivalueSubtrees {
    private class ResultType {
        int count;
        boolean isUni;
        public ResultType(int count, boolean isUni) {
            this.count = count;
            this.isUni = isUni;
        }
    }
    public int countUnivalSubtrees(TreeNode root) {
        return helper(root).count;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (left.isUni && right.isUni && (root.left == null ? true : root.val == root.left.val)
                && (root.right == null ? true : root.val == root.right.val)) {
            return new ResultType(left.count + right.count + 1, true);
        } else {
            return new ResultType(left.count + right.count, false);
        }
    }
}
