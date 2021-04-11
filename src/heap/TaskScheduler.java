package heap;

import java.util.*;

public class TaskScheduler {
    class Wrapper {
        char task;
        int count;
        int pos;
        public Wrapper(char task, int count, int pos) {
            this.task = task;
            this.count = count;
            this.pos = pos;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Wrapper> pq = new PriorityQueue<Wrapper>((a, b) -> b.count-a.count);
        Queue<Wrapper> q = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        for (char key : map.keySet()) {
            pq.add(new Wrapper(key, map.get(key), -(n+1)));
        }
        int index = 0;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek().pos >= index-n) {
                q.add(pq.poll());
            }
            //add IDLE
            if (pq.isEmpty()) {
                while (!q.isEmpty()) {
                    pq.add(q.poll());
                }
            } else {
                Wrapper w = pq.poll();
                while (!q.isEmpty()) {
                    pq.add(q.poll());
                }
                if (w.count > 1) {
                    pq.add(new Wrapper(w.task, w.count-1, index));
                }
            }
            index ++;
        }
        return index;
    }

    //这是别人的greedy O(n)的思路
    //自己借鉴reorganize string的思路会非常非常慢
//    public int leastInterval(char[] tasks, int n) {
//        int[] counter = new int[26];
//        int max = 0;
//        int maxCount = 0;
//        for(char task : tasks) {
//            counter[task - 'A']++;
//            if(max == counter[task - 'A']) {
//                maxCount++;
//            }
//            else if(max < counter[task - 'A']) {
//                max = counter[task - 'A'];
//                maxCount = 1;
//            }
//        }
//
//        int partCount = max - 1;
//        int partLength = n - (maxCount - 1);
//        int emptySlots = partCount * partLength;
//        int availableTasks = tasks.length - max * maxCount;
//        int idles = Math.max(0, emptySlots - availableTasks);
//
//        return tasks.length + idles;
//    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        TaskScheduler t = new TaskScheduler();
        System.out.println(t.leastInterval(tasks, 2));
    }
}
