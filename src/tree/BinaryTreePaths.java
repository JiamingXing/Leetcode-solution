package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(root, res, "");
        return res;
    }
    private void helper(TreeNode root, List<String> res, String s) {
        if (root == null) {
            return;
        }
        s = s + root.val;
        if (root.left == null && root.right == null) {
            res.add(s);
            return;
        }
        if (root.left != null) {
            helper(root.left, res, s+"->");
        }
        if (root.right != null) {
            helper(root.right, res, s+"->");
        }
    }
}

//stringbuilder版本  你想一想我回调sb内容放在最后执行
//那么你碰到leaf 要不要return  自己第一遍就写错了  写成碰到leaf return
//其实不应该 你碰到leaf  还要执行左右两次dfs null return之后回调lead的sb 回到上一层 才是正确的
//那么你sb回调的时候选择什么len你要选择刚进入DFS call的sb状态 因为你执行完这一层的DFS之后
//我要回调会最初的状态回到上一层

/*
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, res, new StringBuilder());
        return res;
    }
    private void dfs(TreeNode root, List<String> res, StringBuilder sb) {
        if (root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
            // return;
        }
        sb.append("->");
        //int len = sb.length();
        dfs(root.left, res, sb);
        dfs(root.right, res, sb);
        sb.setLength(len);
    }
}
 */
