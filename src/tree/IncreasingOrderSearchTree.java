package tree;

//recursive
//怎么想到的...有点像linkedlist的题...
//res = inOrder(root.left) + root + inOrder(root.right)

public class IncreasingOrderSearchTree {
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }
    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }
}


//iterative
/*
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        TreeNode prev = dummy;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr.left = null;
            prev.right = curr;
            prev = curr;
            curr = curr.right;
        }
        return dummy.right;
    }
*/