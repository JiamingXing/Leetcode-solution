package tree;

public class LargestBSTSubtree {
    private class ResultType {
        boolean isBST;
        int count;
        int min;
        int max;
        public ResultType(boolean isBST, int count, int min, int max) {
            this.isBST = isBST;
            this.count = count;
            this.min = min;
            this.max = max;
        }
    }
    int res = 0;
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return res;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (left.isBST && right.isBST && left.max < root.val && right.min > root.val) {
            res = Math.max(res, left.count+right.count+1);
            return new ResultType(true, left.count+right.count+1, Math.min(left.min, root.val), Math.max(right.max, root.val));
        }
        return new ResultType(false, 0, 0, 0);
    }
}
