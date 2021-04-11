package tree;

/*
    LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 */

//这道题要想清楚两个order的traversal代表了什么 各司其职 如果很清楚preorder的只是为了找到子树的root 那我们就能想清楚只需要pre的一个index
//还有递归调用的循环结束条件要想清楚

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        return helper(preorder, inorder, 0, inorder.length-1, 0);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int inStart, int inEnd, int preStart) {
        if (preStart > preorder.length-1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = inStart;
        while (index <= inEnd) {
            if (inorder[index] == root.val) {
                break;
            }
            index ++;
        }
        root.left = helper(preorder, inorder, inStart, index-1, preStart+1);
        root.right = helper(preorder, inorder, index+1, inEnd, preStart+index-inStart+1);
        return root;
    }
}
