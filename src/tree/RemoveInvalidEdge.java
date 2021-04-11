package tree;

import java.util.HashSet;
import java.util.Set;

public class RemoveInvalidEdge {
    public void removeInvalidEdge(TreeNode root) {
        Set<TreeNode> set = new HashSet<>();
        if (root == null) {
            return;
        }
        dfs(root, set);
    }
    private void dfs(TreeNode root, Set<TreeNode> set) {
        if (root.left != null) {
            if (!set.add(root.left)) {
                root.left = null;
            } else {
                dfs(root.left, set);
            }
        }
        if (root.right != null) {
            if (!set.add(root.right)) {
                root.right = null;
            } else {
                dfs(root.right, set);
            }
        }
    }
}
