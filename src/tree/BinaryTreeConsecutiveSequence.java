package tree;

public class BinaryTreeConsecutiveSequence {
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return res;
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = longestConsecutive(root.left);
        int rightCount = longestConsecutive(root.right);
        int left = (root.left != null && root.val + 1 == root.left.val) ? leftCount : 0;
        int right = (root.right != null && root.val + 1 == root.right.val) ? rightCount : 0;
        int cur = Math.max(left, right) + 1;
        res = Math.max(res, cur);
        return cur;
    }
}



/*
public class Solution {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0, root.val);
        return max;
    }

    public void helper(TreeNode root, int cur, int target){
        if(root == null) return;
        if(root.val == target) cur++;
        else cur = 1;
        max = Math.max(cur, max);
        helper(root.left, cur, root.val + 1);
        helper(root.right, cur, root.val + 1);
    }
}
*/