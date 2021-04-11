package binarysearch;

//如何利用complete tree这个关键条件 来优化计算node数量的时间复杂度
//利用高度的性质来计算某一层的点的个数

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}

public class CountCompleteTreeNode {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1;
        }
        int height = 0;
        int sum = 0;
        TreeNode cur = root;
        while (cur.left != null) {
            sum += 1 << height;
            height ++;
            cur = cur.left;
        }
        return sum + helper(root, height);
    }
    //number of nodes at last level for root
    private int helper(TreeNode root, int height) {
        if (height == 1) {
            if (root.right != null) {
                return 2;
            } else if (root.left != null) {
                return 1;
            } else {
                return 0;
            }
        }
        TreeNode mid = root.left;
        int curHeight = 1;
        while (curHeight < height) {
            curHeight ++;
            mid = mid.right;
        }
        if (mid == null) {
            return helper(root.left, height-1);
        } else {
            return 1 << (height - 1) + helper(root.right, height - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(1 << 4);
    }
}
