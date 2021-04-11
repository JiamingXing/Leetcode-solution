package linkedlist;

public class ReverseNodesinKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1 || k == 0) {
            return  head;
        }
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count ++;
        }
        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
                count --;
            }
            //move head to prev one as new head
            head = cur;
        }
        return head;
    }
}
