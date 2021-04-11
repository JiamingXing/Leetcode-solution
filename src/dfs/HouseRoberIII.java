package dfs;

public class HouseRoberIII {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[2];
        res[0] = root.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}

/*
class Solution {
    private class ResultType {
        int maxNode;
        int maxNoneNode;
        public ResultType(int maxNode, int maxNoneNode) {
            this.maxNode = maxNode;
            this.maxNoneNode = maxNoneNode;
        }
    }
    public int rob(TreeNode root) {
        ResultType res = helper(root);
        return Math.max(res.maxNode, res.maxNoneNode);
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        return new ResultType(root.val+left.maxNoneNode+right.maxNoneNode, Math.max(left.maxNode,
                left.maxNoneNode)+Math.max(right.maxNode, right.maxNoneNode));
    }
}
 */
