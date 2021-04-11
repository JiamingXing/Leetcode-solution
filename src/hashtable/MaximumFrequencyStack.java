package hashtable;

//这道题除了想想思路 还要想想实际情况中有没有用到这样一种数据结构的可能性？

import java.util.Iterator;
import java.util.PriorityQueue;

public class MaximumFrequencyStack {

//    public MaximumFrequencyStack() {
//    }
//
//    public void push(int x) {
//
//    }
//
//    public int pop() {
//
//    }
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.offer(1);
        pq.offer(3);
        pq.offer(2);
        System.out.println(pq.peek());
        Iterator iter = pq.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
