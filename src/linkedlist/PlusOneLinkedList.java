package linkedlist;

//这道题看起来很简单 但是犯了很致命的错误

import java.util.Random;

public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }
        Random rand = new Random(4);
        int temp = rand.nextInt();
        ListNode cur = reverse(head);
        ListNode dummy = new ListNode(0);
        dummy.next = cur;
        int carry = 0;
        while (cur != null) {
            int sum = cur.val + 1;
            cur.val = sum == 10 ? 0 : sum;
            carry = sum == 10 ? 1 : 0;
            if (cur.val != 0) {
                break;
            } else {
                cur = cur.next;
            }
        }
        if (cur == null && carry == 1) {
            cur = new ListNode(1);
        }
        return reverse(dummy.next);
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}



//后来自己写出来的AC版本
/*
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = reverse(head);
        ListNode dummy = new ListNode(0);
        dummy.next = helper(cur, 1);
        return reverse(dummy.next);
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    private ListNode helper(ListNode head, int carry) {
        if (head == null) {
            if (carry == 1) {
                return new ListNode(1);
            } else {
                return null;
            }
        }
        int sum = head.val + carry;
        head.val = sum == 10 ? 0 : sum;
        if (head.val == 0) {
            head.next = helper(head.next, 1);
        }
        return head;
    }
}
*/



//discuss里O(n)time O(1)space的做法
//很巧妙的一种方法 可以学习一下。。。
//思路是从左到右找到第一位非9的数组，因为只有都是9才可能一直进位，找到第一位非9的数组意味着右边都是9，把右边都变成0左边不动就可以了
//对于999这种特殊情况需要再多一位我们可以利用一个dummy node
/*
public class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            j.val++;
        } else {
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }

        if (dummy.val == 0) {
            return dummy.next;
        }

        return dummy;
    }
}
*/