package linkedlist;

public class SplitLinkedListinParts {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int[] len = new int[k];
        //计算每一段splited list的长度
        int leng = getLength(root);
        int avrg = leng/k;
        int resid = leng%k;
        for (int i = 0; i < k; i++) {
            len[i] = avrg + (resid == 0 ? 0 : 1);
            resid = (resid == 0 ? 0 : resid-1);
        }
        ListNode cur = root;
        for (int i = 0; i < k; i++) {
            if (len[i] == 0) {
                res[i] = null;
            } else {
                res[i] = cur;
                ListNode tail = helper(cur, len, i);
                cur = tail.next;
                tail.next = null;
            }

        }
        return res;
    }
    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int res = 0;
        while (head != null) {
            res ++;
            head = head.next;
        }
        return res;
    }
    private ListNode helper(ListNode head, int[] len, int pos) {
        int count = len[pos];
        ListNode cur = head;
        while (count > 1) {
            cur = cur.next;
            count --;
        }
        return cur;
    }
}
