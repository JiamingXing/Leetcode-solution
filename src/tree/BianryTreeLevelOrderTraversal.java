package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BianryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> Q = new LinkedList<>();
        Q.offer(root);
        while (!Q.isEmpty()) {
            int size = Q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = Q.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    Q.offer(cur.left);
                }
                if (cur.right != null) {
                    Q.offer(cur.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
}
