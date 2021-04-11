package linkedlist;

//Cycle 这道题算是比较巧妙的利用快慢指针 如果没想到 记住这样的用法就行
//这种快慢指针的方法 随便画个图是可以 证明的 如果要找cycle的入口 会更有技巧性一些 到时候可以证明一下

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
