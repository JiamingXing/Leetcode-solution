package tree;

//这道题要利用BST的性质 我们可以根据这个性质找到第一个满足条件的节点 看在区间的哪一边，可以保证节点的左右子树有一个是正确的


public class TrimBST {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode cur = root;
        while (cur != null && (cur.val < L || cur.val > R)) {
            if (cur.val < L) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (cur == null) {
            return null;
        }
        root = cur;
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
