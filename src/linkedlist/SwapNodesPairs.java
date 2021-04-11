package linkedlist;

//挺好的一道题 如果第一次做可能想不到用递归DFS的做法

public class SwapNodesPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return swap(head, head.next);
    }
    private ListNode swap(ListNode A, ListNode B) {
        ListNode temp = B.next;
        B.next = A;
        if (temp == null) {
            A.next = null;
        } else if (temp.next == null) {
            A.next = temp;
        } else {
            A.next = swap(temp, temp.next);
        }
        return B;
    }
}
