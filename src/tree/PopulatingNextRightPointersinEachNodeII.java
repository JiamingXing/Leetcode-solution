package tree;



//这道题每次重新做自己想的时候都不容易形成思路，首先我们要想一个问题，我们要确定当前node的next的时候我们只能根据潜在的next的parent确定
//这是一个很关键的点，如果tree是perfect的话那么我们直接可以根据parent的next关系确定，像这道题不是perfect，我们就要想办法确定

//因为tree是not perfect所以在同一层建立next关系的关键点在于什么？在于我们需要设置一个prev指针来指向当前存在的children

//这道题还有一个细节就是我每次循环都先把head设成null 这一点可能很难想到，比较直接的思路是我每次都更新正确的head位置就可以了
//但是如果不设成null 在最后一个level的时候 cur循环整个level没有任何的children 那么head值将不会更新 会进入死循环！



public class PopulatingNextRightPointersinEachNodeII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = root;
        TreeLinkNode cur = null;
        TreeLinkNode prev = null;
        while (head != null) {
            cur = head;
            head = null;
            prev = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                cur = cur.next;
            }
        }
    }
}
