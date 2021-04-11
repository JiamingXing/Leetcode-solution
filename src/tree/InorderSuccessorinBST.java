package tree;

//分析清楚 在BST里找某一个node的successor 首先判断这个node是否有right subtree 如果与就是right subtree里的left-most node
//如果没有那么我们就在parent里面找 找到最接近的parent 它通过left-child的path可以到达p

public class InorderSuccessorinBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode iter = null;
        TreeNode prev = null;
        if (p.right != null) {
            iter = p.right;
            while (iter.left != null) {
                iter = iter.left;
            }
            return iter;
        } else {
            iter = root;
            while (iter != p) {
                if (iter.val < p.val) {
                    iter = iter.right;
                } else {
                    prev = iter;
                    iter = iter.left;
                }
            }
        }
        return prev;
    }
}


//recursive的写法
//大于等于的时候往右走 就是还要继续往右子树搜索
/*
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        }
    }
}
*/