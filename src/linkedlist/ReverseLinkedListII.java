package linkedlist;

//这道题很好 想一想怎么形成思路？
//思路是我首先要找到第m个节点的位置，并且记录第m-1个节点 从第m个节点开始reverse之后直到第n个节点 并且记录第n+1个节点
//让原来的第m个节点指向第n+1个节点
//那么这个时候分析我们需要记录哪些东西？ 必须要记录的是第m-1 m n n+1至少需要4个指针

//这里复习的时候写法 变量的命名规则有点紊乱
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode prev = dummy;
        for (int i = 0; i < m; i++) {
            prev = cur;
            cur = cur.next;
        }
        ListNode iter = cur;
        ListNode post = iter.next;
        for (int i = m; i < n; i++) {
            ListNode temp = post.next;
            post.next = iter;
            iter = post;
            post = temp;
        }
        prev.next = iter;
        cur.next = post;
        return dummy.next;
    }
}





/*
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode prev = head;
        ListNode start = head.next;
        ListNode cur = start;
        ListNode post = start.next;
        for (int i = m; i < n; i++) {
            ListNode temp = post.next;
            post.next = cur;
            cur = post;
            post = temp;
        }
        prev.next = cur;
        start.next = post;
        return dummy.next;
    }
}
*/