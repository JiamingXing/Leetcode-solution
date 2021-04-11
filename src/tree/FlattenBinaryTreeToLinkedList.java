package tree;

/*
    114. Flatten Binary Tree to Linked List
 */

//这道题最关键的思路在于 我们要想清楚要形成链表我们应该从最后一个点出发 这样可以帮助我们想到用DFS
//每次复习的时候都想不起来这道题是怎么做的

public class FlattenBinaryTreeToLinkedList {
    private TreeNode prev;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        prev = root;
        root.left = null;
    }
}





//post order traversal的思路
//这个思路本质上和上面的是一样的 但是这种更好思考一些
/*
public class Solution {
    public void Flatten(TreeNode root) {
        solve(root, null);
    }
    TreeNode solve(TreeNode root, TreeNode last)    {
        if(root == null) return last;
        root.right = solve(root.left, solve(root.right, last));
        root.left = null;
        return root;
    }
}
*/