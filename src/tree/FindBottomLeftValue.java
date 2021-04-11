package tree;

import java.util.LinkedList;
import java.util.Queue;

//也可以先把右节点入堆，找到最后一个节点即可
public class FindBottomLeftValue {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        TreeNode node = null;
        while(!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = Q.poll();
                if (i == 0) {
                    node = cur;
                }
                if (cur.left != null) {
                    Q.offer(cur.left);
                }
                if (cur.right != null) {
                    Q.offer(cur.right);
                }
            }
        }
        return node.val;
    }
}
