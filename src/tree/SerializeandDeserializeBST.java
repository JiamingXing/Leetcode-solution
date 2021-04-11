package tree;

//类似于加密解密压缩的考察
//拿到这道题很懵逼 一颗tree我怎么让他变成独一无二的 可以压缩解压
//这道题给的还是个BST 要学会如何利用BST的性质 左子树比根小 右子树比根大
//所以我们可以用preorder进行压缩 然后recursive的进行解压

public class SerializeandDeserializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(",");
        TreeNode root = buildBST(nodes, 0, nodes.length-1);
        return root;
    }

    private TreeNode buildBST(String[] nodes, int start, int end) {
        if (start == end) {
            return new TreeNode(Integer.parseInt(nodes[start]));
        }
        if (start > end) {
            return null;
        }
        int val = Integer.parseInt(nodes[start]);
        TreeNode root = new TreeNode(val);
        int iter = start+1;
        while (iter <= end && Integer.parseInt(nodes[iter]) < val) {
            iter ++;
        }
        if (iter == start+1) {
            root.left = null;
            root.right = buildBST(nodes, iter, end);
        } else if (iter == end+1) {
            root.left = buildBST(nodes, start+1, end);
            root.right = null;
        } else {
            root.left = buildBST(nodes, start+1, iter-1);
            root.right = buildBST(nodes, iter, end);
        }
        return root;
    }
}
