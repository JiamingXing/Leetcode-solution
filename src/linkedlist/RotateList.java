package linkedlist;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int len = getLength(head);
        k = k % len;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return  dummy.next;
    }
    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int res = 0;
        while (head != null) {
            head = head.next;
            res ++;
        }
        return res;
    }
}
