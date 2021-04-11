package tree;

public class BinaryTreeLongestConsecutiveSequenceII {
    private class ResultType {
        int val;
        int decLen;
        int incLen;
        public ResultType(int val, int incLen, int decLen) {
            this.val = val;
            this.decLen = decLen;
            this.incLen = incLen;
        }
    }
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        int incLen = 1;
        int decLen = 1;
        if (root.val == left.val+1) {
            incLen = Math.max(incLen, left.incLen+1);
        }
        if (root.val == left.val-1) {
            decLen = Math.max(decLen, left.decLen+1);
        }
        if (root.val == right.val+1) {
            incLen = Math.max(incLen, right.incLen+1);
        }
        if (root.val == right.val-1) {
            decLen = Math.max(decLen, right.decLen+1);
        }
        max = Math.max(max, incLen);
        max = Math.max(max, decLen);
        if (root.val == left.val+1 && root.val == right.val-1) {
            max = Math.max(max, left.incLen + right.decLen +1);
        }
        if (root.val == left.val-1 && root.val == right.val+1) {
            max = Math.max(max, left.decLen +right.incLen+1);
        }
        return new ResultType(root.val, incLen, decLen);
    }
}
