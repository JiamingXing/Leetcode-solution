package linkedlist;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyMin = new ListNode(0);
        ListNode dummyMax = new ListNode(0);
        ListNode curMin = dummyMin;
        ListNode curMax = dummyMax;
        while (head != null) {
            if (head.val < x) {
                curMin.next = head;
                curMin = curMin.next;
            } else {
                curMax.next = head;
                curMax = curMax.next;
            }
            head = head.next;
        }
        curMax.next = null;
        curMin.next = dummyMax.next;
        return dummyMin.next;
    }
}
