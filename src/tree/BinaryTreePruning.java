package tree;

//这是正确的写法 看看之前没AC的写法。。bug在哪里？

public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}


//这样的写法 判断条件之后改变root指向null 但是并不会改变上一层的root.left 依旧指向一个node 而不是null
//root = null 只是改变了root的指针指向一个null node 而不会改变这个原本root指向的node的值 所以我们判断我们当前node是否为null之后返回在parent的child进行改变
/*
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = pruneTree(root.left);
        TreeNode right = pruneTree(root.right);
        if (left == null && right == null && root.val == 0) {
            root = null;
        }
        return root;
    }
}
*/