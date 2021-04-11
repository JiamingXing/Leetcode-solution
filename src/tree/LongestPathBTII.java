package tree;

import java.util.ArrayList;
import java.util.List;

public class LongestPathBTII {
    int max = 0;
    List<Integer> resPath = new ArrayList<>();
    public List<Integer> printLongestPathBT(TreeNode root) {
        dfs(root);
        return resPath;
    }
    private List<Integer> dfs(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> left = dfs(root.left);
        List<Integer> right = dfs(root.right);
        int leftDep = left.size();
        int rightDep = right.size();
        if (leftDep > rightDep) {
            res = new ArrayList<>(left);
        } else {
            res = new ArrayList<>(right);
        }
        res.add(root.val);
        if (leftDep + rightDep + 1 > max) {
            resPath = new ArrayList<>(left);
            resPath.add(root.val);
            for (int i = right.size()-1; i >= 0; i--) {
                resPath.add(right.get(i));
            }
        }
        return res;
    }
}
