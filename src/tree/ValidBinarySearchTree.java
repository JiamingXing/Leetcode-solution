package tree;

/*
    98. Valid Binary Search Tree
 */

public class ValidBinarySearchTree {
    private class ResultType {
        int min;
        int max;
        boolean isValid;
        ResultType(int max, int min, boolean isValid) {
            this.max = max;
            this.min = min;
            this.isValid = isValid;
        }
    }
    public boolean isValidBST(TreeNode root) {
        return helper(root).isValid;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (!left.isValid || !right.isValid) {
            return new ResultType(0, 0, false);
        }
        if (root.left != null && left.max >= root.val || root.right != null && right.min <= root.val) {
            return new ResultType(0, 0, false);
        }
        return new ResultType(Math.max(right.max, root.val), Math.min(left.min, root.val), true);
    }
}




//这样写没考虑到一个corner case 只有一个root值为MAX/MIN
//二刷的时候还是犯了这么错误 依旧没考虑到这个情况
/*
public class ValidBinarySearchTree {
    private class ResultType {
        int min;
        int max;
        boolean isValid;
        ResultType(int max, int min, boolean isValid) {
            this.max = max;
            this.min = min;
            this.isValid = isValid;
        }
    }
    public boolean isValidBST(TreeNode root) {
        return helper(root).isValid;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (!left.isValid || !right.isValid) {
            return new ResultType(0, 0, false);
        }
        if (left.max < root.val && right.min > root.val) {
            return new ResultType(Math.max(root.val, right.max), Math.min(root.val, left.min), true);
        } else {
            return new ResultType(0, 0, false);
        }
    }
}
*/