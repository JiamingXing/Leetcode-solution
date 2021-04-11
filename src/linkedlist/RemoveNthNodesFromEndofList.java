package linkedlist;

public class RemoveNthNodesFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        ListNode prev = dummy;
        while (n > 0) {
            fast = fast.next;
            n --;
        }
        while (fast != null ) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        return dummy.next;
    }
}
