package tree;

import java.util.ArrayList;
import java.util.List;

//这道题最关键在于能看出这是一道从底向上操作的题目，所以肯定是用DFS做
//不能被这道题的表面思路和例子所限制思路，如果想着先找到所有叶子移除再找剩下的所有叶子就会卡住
//而是根据depth把访问的节点放到该放的地方去

public class FindLeavesofBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private int helper(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }
        int curDepth = Math.max(helper(root.left, res), helper(root.right, res)) + 1;
        if (res.size() < curDepth + 1) {
            res.add(new ArrayList<>());
        }
        res.get(curDepth).add(root.val);
        return curDepth;
    }
}
