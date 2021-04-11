package tree;

/*
    LeetCode 124. Binary Tree Maximum Path Sum
 */

public class BinaryTreeMaximumPathSum {
    private class ResultType{
        int onePass;
        int max;
        ResultType(int onePass, int max) {
            this.onePass = onePass;
            this.max = max;
        }
    }
    public int maxPathSum(TreeNode root) {
        return helper(root).max;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        int curOnepass = Math.max(Math.max(left.onePass, 0)+root.val, Math.max(right.onePass, 0) + root.val);
        int curMax = Math.max(left.max, Math.max(right.max, root.val+Math.max(left.onePass, 0)+Math.max(right.onePass,0)));
        return new ResultType(curOnepass, curMax);
    }
}

//第二次写的简洁版本 用一个全局变量
/*
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    //return the maximum path sum from current root to single path
    //compare the maximum path sum meanwhile
    private int helper(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int bothMax = root.val + (left >= 0 ? left : 0) + (right >= 0 ? right : 0);
        max = Math.max(max, bothMax);
        return root.val + Math.max(left >= 0 ? left : 0, right >= 0 ? right : 0);
    }
}
*/


//差不多的思路 但是这样更简洁 不用写一个新的类 DFS
/*
public class BinaryTreeMaximumPathSum {
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;

    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        maxValue = Math.max(maxValue, left+right+root.val);
        return Math.max(left, right) + root.val;
    }
}
*/
