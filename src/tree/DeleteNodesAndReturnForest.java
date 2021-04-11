package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//最初版本写完 感觉自己写了很多重复代码 应该可以继续抽象成一个method...

public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int delete : to_delete) {
            set.add(delete);
        }
        if (!set.contains(root.val)) {
            res.add(root);
            dfsAlive(root, res, set);
        } else {
            dfsDelete(root, res, set);
        }
        return res;
    }
    private void dfsDelete(TreeNode root, List<TreeNode> res, Set<Integer> set) {
        if (root == null) {
            return;
        }
        if (root.left != null && !set.contains(root.left.val)) {
            TreeNode left = root.left;
            root.left = null;
            res.add(left);
            dfsAlive(left, res, set);
        } else {
            dfsDelete(root.left, res, set);
        }
        if (root.right != null && !set.contains(root.right.val)) {
            TreeNode right = root.right;
            root.right = null;
            res.add(right);
            dfsAlive(right, res, set);
        } else {
            dfsDelete(root.right, res, set);
        }
    }
    private void dfsAlive(TreeNode root, List<TreeNode> res, Set<Integer> set) {
        if (root == null) {
            return;
        }
        if (root.left != null && set.contains(root.left.val)) {
            TreeNode left = root.left;
            root.left = null;
            dfsDelete(left, res, set);
        } else {
            dfsAlive(root.left, res, set);
        }
        if (root.right != null && set.contains(root.right.val)) {
            TreeNode right = root.right;
            root.right = null;
            dfsDelete(right, res, set);
        } else {
            dfsAlive(root.right, res, set);
        }
    }
}

//看看别人写的...
//说实话 这个思路才是正确的思路
//每个node是否delete 只取决于 当前node的val
//如果delete我们就return一个null 为的是更新parent的结构
//而一个node是否是forest中tree的root 取决于它的parent是否delete
//如果parent delete了那么child就是root
//而当时可能纠结的点在于 我们parent delete了 所以child是root 但是dfs到child的时候也要delete
//其实稍微想一下就懂了 只有当当前node不被delete并且是root的时候才进res
//这样整个递归结构关系我们就找到了
//至于root这个node 我们只要传一个true进去就好了。。。 因为root必然是root 是否要delete没什么关系的
//自己上面写的思路虽然AC 但是有点类似于 就是如果发现delete的node就一直找delete node 如果不delete就一直找不delete
//其实很蠢 不是这道题的初衷
/*
    Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted) res.add(node);
        node.left = helper(node.left, deleted);
        node.right =  helper(node.right, deleted);
        return deleted ? null : node;
    }

 */
