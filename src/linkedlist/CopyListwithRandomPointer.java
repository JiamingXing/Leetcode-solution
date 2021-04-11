package linkedlist;

//关于deep copy的题不陌生了 不管是copy graph 还是这道题 核心思路：建立new node和 origin node之间的联系
//如果我们允许使用extra space 我们可以用一个hashmap建立new node和 original node的一一对应关系
//如果不能使用 我们就要用这种方法建立联系

public class CopyListwithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode cur = head;
        RandomListNode next = null;
        //copy node
        while (cur != null) {
            next = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = next;
            cur = next;
        }
        //copy random
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        //retireve origin list
        //这一步是最难的
        //一开始很可能会想我从两个list的头开始往后走 但是这样不对
        //因为我要概念原来指针的next 我很可能找不到新node的next
        //所以我们可能需要一个prev
        cur = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode prev = dummy;
        while (cur != null) {
            prev.next = cur.next;
            next = cur.next.next;
            prev = cur.next;
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }
}



/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
//二刷写的 中间还是出了问题
/*
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node iter = head;
        //copy to single list
        while (iter != null) {
            Node copyNode = new Node(iter.val, iter.next, null);
            iter.next = copyNode;
            iter = iter.next.next;
        }
        //copy random
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        //break single list to two list
        Node dummy = new Node(0);
        Node copy = dummy;
        iter = head;
        while (iter != null) {
            copy.next = iter.next;
            iter.next = copy.next.next;
            copy = copy.next;
            iter = iter.next;
        }
        return dummy.next;
    }
}
 */