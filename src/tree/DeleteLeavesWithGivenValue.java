package tree;

public class DeleteLeavesWithGivenValue {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        TreeNode left = removeLeafNodes(root.left, target);
        TreeNode right = removeLeafNodes(root.right, target);
        root.left = left;
        root.right = right;
        if (left == null && right == null && root.val == target) {
            return null;
        }
        return root;
    }
}
