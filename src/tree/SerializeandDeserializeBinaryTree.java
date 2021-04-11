package tree;

//关于serialize和deserialize的问题 有点像解密加密的问题 会想到什么样的思路
//首先如果是比较简单的BST 会想到什么思路 如何定义一颗BST的唯一性 可以想到用preorder traversal 因为BST左子树小于root 右子树大于root
//但是如果变成一颗不同的binary tree思路会有怎样的不同 用两个traversal？ pre+in？

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        buildString(root.left, sb);
        buildString(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));
        TreeNode root = buildTree(nodes);
        return root;
    }

    private TreeNode buildTree(Queue<String> Q) {
        String cur = Q.poll();
        if (cur.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = buildTree(Q);
        root.right = buildTree(Q);
        return root;
    }
}
