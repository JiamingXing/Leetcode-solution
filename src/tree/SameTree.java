package tree;

/*
    LeetCode 100. Same Tree
 */


public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        if (left && right && p.val == q.val) {
            return true;
        } else {
            return false;
        }
    }
}
