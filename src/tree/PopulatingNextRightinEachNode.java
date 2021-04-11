package tree;

public class PopulatingNextRightinEachNode {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode levelStart = root.left;
        while (levelStart != null) {
            TreeLinkNode cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
    }
}
