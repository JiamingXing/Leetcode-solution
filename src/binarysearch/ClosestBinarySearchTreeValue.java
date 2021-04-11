package binarysearch;

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        TreeNode next = target < root.val ? root.left : root.right;
        if (next == null) {
            return root.val;
        }
        int temp = closestValue(next, target);
        return Math.abs(target - temp) < Math.abs(target - root.val) ? temp : root.val;
    }
}




//自己当时做的时候做错了。。。我们沿着一条path走必须要走到底才有可能找到答案
/*
public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        return helper(root, target, 0, 0);
    }
    private int helper(TreeNode root, double target, int prev, int stat) {
        if (root == null) {
            return prev;
        }
        if ((stat == -1 && root.val <= target) || stat == 1 && root.val >= target) {
            double dif1 = Math.abs(target - prev);
            double dif2 = Math.abs(target - root.val);
            return dif1 > dif2 ? root.val : prev;
        }
        if (target < root.val) {
            return helper(root.left, target, root.val, -1);
        } else if (target > root.val) {
            return helper(root.right, target, root.val, 1);
        } else {
            return root.val;
        }
    }
}
*/