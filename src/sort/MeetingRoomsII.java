package sort;

//这道题反正不是自己想出来的 而且复习的时候不容易形成思路 还是要从根本思考一下 思路如何形成？
//这道题有点贪心算法的思想在里面
//除非当前房间可以给下一个会议使用 不然都维持当前的end 因为符合潜在条件end > start

//你这么想啊 我把start和end分开之后 我们分析每个start 对于这个start呢 有两种选择
//1.和当前在使用的meeting room是有重叠的 必须分配一个新的
//2.某一个meeting room结束了 是用它的meeting room
//那么我当前的start和哪个end比？ 肯定和最先结束的end 也就是从小到大的顺序进行比较

import java.util.Arrays;

public class MeetingRoomsII {
    public int minMeetingRooms(sort.Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int curEnd = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[curEnd]) {
                res ++;
            } else {
                curEnd ++;
            }
        }
        return res;
    }
}



//也可以使用pq的思路 我们可以把share一个房间的meeting 根据end进行归并 如果需要独立房间那就放进pq中
//最后pq的size就是需要的房间数量
/*
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) { return a.start - b.start; }
        });

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) { return a.end - b.end; }
        });

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }
*/
