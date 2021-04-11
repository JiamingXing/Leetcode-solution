package dfs;

import java.util.ArrayList;
import java.util.List;

public class HouseRobberIV {
    private class TreeNode {
        int val;
        List<TreeNode> children = new ArrayList<>();
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    private int[] helper(TreeNode root) {
        int[] res = new int[2];
        res[0] = root.val;
        for (TreeNode child : root.children) {
            int[] temp = helper(child);
            res[0] += temp[1];
            res[1] += Math.max(temp[0], temp[1]);
        }
        return res;
    }
}
