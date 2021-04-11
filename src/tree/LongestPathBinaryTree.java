package tree;

import java.util.ArrayList;
import java.util.List;

public class LongestPathBinaryTree {
    int max;
    List<Integer> resPath = new ArrayList<>();
    public int longestPathBT(TreeNode root) {
        dfs(root);
        return max;
    }
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right + 1);
        return Math.max(left, right) + 1;
    }

}
