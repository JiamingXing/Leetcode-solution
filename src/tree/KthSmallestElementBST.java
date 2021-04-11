package tree;

import java.util.Stack;

//follow up: if the bst is modified frequently(inserted/deleted) often, how would you optimize your algorithm


//DFS iterative version
public class KthSmallestElementBST {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (k != 0) {
            TreeNode node = stack.pop();
            k --;
            if (k == 0) {
                return node.val;
            }
            TreeNode right = node.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }
}


//binary search version
/*
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }

        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null) return 0;

        return 1 + countNodes(n.left) + countNodes(n.right);
    }
*/



//DFS recursive version
/*
    // better keep these two variables in a wrapper class
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }
*/