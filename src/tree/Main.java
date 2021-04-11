package tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        DistributeCoinsinBinaryTree d = new DistributeCoinsinBinaryTree();
        System.out.println(d.distributeCoins(root));
    }
}
