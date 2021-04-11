package linkedlist;


//复习的时候写的错误版本 出现无限循环了 所以tail的next必须要弄成null保证不出现无限循环，就是cur之前的一段list必须有尽头
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head.next;
        ListNode tail = head;
        ListNode prev = null;
        ListNode iter = null;
        while (cur != null) {
            prev = dummy;
            iter = dummy.next;
            while (iter != cur) {
                if (iter.val > cur.val) {
                    break;
                }
                prev = iter;
                iter = iter.next;
            }
            ListNode next = cur.next;
            if (iter != cur) {
                prev.next = cur;
                cur.next = iter;
            } else {
                tail = cur;
            }
            cur = next;
        }
        tail.next = null;
        return dummy.next;
    }
}




//之前自己写的版本
/*
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next= head;
        ListNode iter = head.next;
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        ListNode tail = dummy.next;
        tail.next = null;
        while (iter != null) {
            while (cur != null && cur.val < iter.val) {
                prev = cur;
                cur = cur.next;
            }
            ListNode next = iter.next;
            if (cur == null) {
                prev.next = iter;
                tail = iter;
                tail.next = null;
            } else {
                prev.next = iter;
                iter.next = cur;
            }
            prev = dummy;
            cur = dummy.next;
            iter = next;
        }
        return dummy.next;
    }
}
*/
