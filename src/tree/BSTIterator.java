package tree;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode res = stack.pop();
        if (res.right != null) {
            TreeNode iter = res.right;
            while (iter != null) {
                stack.push(iter);
                iter = iter.left;
            }
        }
        return res.val;
    }
}
