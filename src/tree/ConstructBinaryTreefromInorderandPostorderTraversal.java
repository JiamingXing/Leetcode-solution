package tree;

/*
    106. Construct Binary Tree from Inorder and Postorder Traversal
 */

public class ConstructBinaryTreefromInorderandPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        return helper(inorder, postorder, 0, inorder.length-1, postorder.length-1);
    }
    private TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd, int postIndex) {
        if (inStart > inEnd || postIndex < 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIndex]);
        int index = inStart;
        while (index <= inEnd) {
            if (inorder[index] == root.val) {
                break;
            }
            index ++;
        }
        root.left = helper(inorder, postorder, inStart, index-1, postIndex-inEnd+index-1);
        root.right = helper(inorder, postorder, index+1, inEnd, postIndex-1);
        return root;
    }
}
