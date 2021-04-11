package tree;

import java.util.ArrayList;
import java.util.List;

//注释掉的代码 是做backtracking的时候 return和remove位置错误的情况 当时复习想这个问题想了一会儿 总结一下
//因为我们可以允许在叶子节点的时候继续往下做DFS 因为null的时候直接return 所以我们把remove之前保存的元素放在left和right之后才是对的
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(root, sum, res, temp);
        return res;
    }
    private void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> temp) {
        if (root == null) {
            return;
        }
        temp.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                res.add(new ArrayList<>(temp));
            }
//            temp.remove(temp.size() - 1);
//            return;
        }
        helper(root.left, sum - root.val, res, temp);
        helper(root.right, sum - root.val, res, temp);
        temp.remove(temp.size() - 1);
    }
}
