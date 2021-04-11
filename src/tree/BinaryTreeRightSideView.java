package tree;

/*
    199. Binary Tree Right Side View
 */

import java.util.ArrayList;
import java.util.List;

//BFS的做法思路简单直接 但是DFS思路老是会想不起来 思考一下
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, 0, res);
        return res;
    }
    private void helper(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(root.val);
        }
        level ++;
        helper(root.right, level, res);
        helper(root.left, level, res);
    }
}
