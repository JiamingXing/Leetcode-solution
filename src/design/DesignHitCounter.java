package design;

import java.util.LinkedList;
import java.util.Queue;

public class DesignHitCounter {
    Queue<Integer> Q;
    /** Initialize your data structure here. */
    public DesignHitCounter() {
        this.Q = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        Q.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (Q.isEmpty() && Q.peek() <= timestamp-300) {
            Q.poll();
        }
        return Q.size();
    }
}




//一个O(1)空间的思路
//public class HitCounter {
//    private int[] times;
//    private int[] hits;
//    /** Initialize your data structure here. */
//    public HitCounter() {
//        times = new int[300];
//        hits = new int[300];
//    }
//
//    /** Record a hit.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public void hit(int timestamp) {
//        int index = timestamp % 300;
//        if (times[index] != timestamp) {
//            times[index] = timestamp;
//            hits[index] = 1;
//        } else {
//            hits[index]++;
//        }
//    }
//
//    /** Return the number of hits in the past 5 minutes.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public int getHits(int timestamp) {
//        int total = 0;
//        for (int i = 0; i < 300; i++) {
//            if (timestamp - times[i] < 300) {
//                total += hits[i];
//            }
//        }
//        return total;
//    }
//}




//因为时间永远是增加的 所以我们可以用一个Q 需要geiHit的时候就poll
//public class HitCounter {
//    Queue<Integer> q = null;
//    /** Initialize your data structure here. */
//    public HitCounter() {
//        q = new LinkedList<Integer>();
//    }
//
//    /** Record a hit.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public void hit(int timestamp) {
//        q.offer(timestamp);
//    }
//
//    /** Return the number of hits in the past 5 minutes.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public int getHits(int timestamp) {
//        while(!q.isEmpty() && timestamp - q.peek() >= 300) {
//            q.poll();
//        }
//        return q.size();
//    }
//}
