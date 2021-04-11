package tree;

public class SecondMinimumNodeBT {
    int min = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return min == Integer.MAX_VALUE ? -1 : root.val+min;
    }
    private void dfs(TreeNode root, int target) {
        if (root.val > target) {
            if (root.val - target < min) {
                min = root.val - target;
            }
            return;
        }
        if (root.left != null) {
            dfs(root.left, target);
            dfs(root.right, target);
        }
    }
}
