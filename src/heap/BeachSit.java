package heap;

//板凳上坐着一些人用1表示 找0的地方插入 似的它离两边相邻的人尽可能远 找那个位置
//follow up: 一直插入直到板凳上坐满人 按顺序返回所有坐标
//给你一个空板凳 每次插入都符合最远的条件 按顺序返回所有坐标

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BeachSit {
    public int findPosition(int[] nums) {
        int max = 1, start = -1, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i-start > max) {
                    res = start + (i - start) / 2;
                    max = i-start;
                }
                start = i;
            }
        }
        if (nums.length - start > max) {
            res = start + (nums.length - start) / 2;
        }
        return res;
    }

    public List<Integer> findSitSequence(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int start = -1;
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
           @Override
           public int compare(Interval o1, Interval o2) {
               return o2.len - o1.len;
           }
        });
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                pq.offer(new Interval(start, i));
                start = i;
            }
        }
        while (!pq.isEmpty() && pq.peek().len > 1) {
            Interval cur = pq.poll();
            int mid = cur.start + (cur.end - cur.start) / 2;
            res.add(mid);
            pq.offer(new Interval(cur.start, mid));
            pq.offer(new Interval(mid, cur.end));
        }
        return res;
    }

    private class Interval {
        int start;
        int end;
        int len;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.len = this.end - this.start;
        }
    }
}
