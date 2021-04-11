package linkedlist;

//这道题的时间复杂度怎么分析 还有怎么用priorityqueue来做？

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }
    private ListNode helper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (end == start + 1) {
            return merge(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid+1, end);
        return merge(left, right);
    }
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int val2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (val1 < val2) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}




//PQ的做法
/*
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        //PQ始终维持lists size的大小
        PriorityQueue<ListNode> Q = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                Q.add(lists[i]);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!Q.isEmpty()) {
            ListNode node = Q.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                Q.add(node.next);
            }
        }
        return dummy.next;
    }
}
*/
