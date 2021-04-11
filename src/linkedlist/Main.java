package linkedlist;

public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        InsertionSortList s = new InsertionSortList();
        ListNode res = s.insertionSortList(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
