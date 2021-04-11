package tree;

//只需要遍历所有点的时候 判断下child里有没有left leaf就可以了
public class SumofLeftLeave {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper(root);
        return sum;
    }
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        helper(root.left);
        helper(root.right);
    }
}

//这个写法更舒服好看 也不需要global variable
/*
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) ans += root.left.val;
            else ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);

        return ans;
    }
}
*/
