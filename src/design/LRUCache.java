package design;

//design的题是自己的弱势 对于设计题缺乏经验 这和实际工作经验和平时自己的思考深度都有关
//这道超级高频题 不管做几次都会一脸懵逼 这次复习好好静下心来思考一下这道题

//首先支持get put类似于hashmap的操作那我们肯定需要用一个hashmap去实现
//但是还要支持如果这个map的size满了 我们就移除least recently used key-val pair
//在一个Map中你怎么知道你哪个key是least recently used?

//想了一下之后真的想不到怎么去构造这个东西。。 不过没有关系 第一次碰到这个问题的话确实会比较南翔
//但是你可以看一下人家怎么实现的 思考一下怎么想到这个实现的？
//双向链表和Deque有关系吗？
//对于一个双向链表来说我们都可以做到O(1)的时间add或者remove某个node 因为每个node都有prev和post的信息

//其实对于双向链表的操作 我觉得应该是不难的 难的是你怎么想到我一个LRU是用双向链表实现
//我没put一个新的值 都是一次recently used 所以我应该把这对key-value pair移动到链表的最前端
//与之对应的 我们链表的最末端就是我们的没有使用过的值 所以如果达到capacity我们只需要把最后一个值移除就行了
//这是总体思路 我觉得很难想到的就是 你会纠结在map的内部结构上 而不是想着说根据key找value
//这个value我们可以处理一下放在一个双向链表里

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode post;
    }

    int cap;
    int count;
    DLinkedNode head;
    DLinkedNode tail;
    Map<Integer, DLinkedNode> map;

    //insert at head
    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.post = head.post;
        head.post.prev = node;
        head.post = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode post = node.post;
        prev.post = post;
        post.prev = prev;
    }

    private void moveHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        this.removeNode(res);
        return res;
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cap = capacity;
        this.count = 0;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        head.post = tail;
        tail.prev = head;
        head.prev = null;
        tail.post = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            //throw exception
            return -1;
        } else {
            DLinkedNode node = map.get(key);
            this.moveHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            DLinkedNode node = new DLinkedNode();
            node.key = key;
            node.val = value;
            map.put(key, node);
            this.addNode(node);
            if (++ count > this.cap) {
                DLinkedNode cur = this.popTail();
                this.map.remove(cur.key);
                count --;
            }
        } else {
            DLinkedNode node = map.get(key);
            node.val = value;
            this.moveHead(node);
        }
    }
}
