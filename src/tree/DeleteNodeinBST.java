package tree;

//这种题主要考察分类讨论的全面程度 换句话说就是corner case考虑周全
//整体的思路就是 把删除节点的左子树放到右子树的最小节点的左子树

public class DeleteNodeinBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode pre = null;
        TreeNode iter = root;
        int preStat = 0;
        while (iter != null && iter.val != key) {
            pre = iter;
            if (iter.val < key) {
                iter = iter.right;
                preStat = 1;
            } else {
                iter = iter.left;
                preStat = 0;
            }
        }
        //can not find key
        if (iter == null) {
            return root;
        }
        //key is root
        if (pre == null) {
            //if no subtree return left subtree directly
            if (root.right == null) {
                root = root.left;
            } else {
                //put left subtree into the leftsubtree of the minimum node in right subtree
                TreeNode left = root.left;
                root = root.right;
                iter = root;
                while (iter.left != null) {
                    iter = iter.left;
                }
                iter.left = left;
            }
        } else {
            if (preStat == 1) {
                pre.right = iter.right;
                TreeNode node = pre.right;
                while (node != null && node.left != null) {
                    node = node.left;
                }
                if (node == null) {
                    pre.right = iter.left;
                } else {
                    node.left = iter.left;
                }
            } else {
                pre.left = iter.right;
                TreeNode node = pre.left;
                while (node != null && node.left != null) {
                    node = node.left;
                }
                if (node == null) {
                    pre.left = iter.left;
                } else {
                    node.left = iter.left;
                }
            }
        }
        return root;
    }
}
