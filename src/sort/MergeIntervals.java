package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//先根据start sort intervals ？ 怎么sort 自己写个comparator 或者分别sort start和end
//不过不管用哪种方法写，核心都是 不断探索list 如果发现下一层的start比上一层的end大 就表明 interval断开不继续粘粘了

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        intervals.sort(new Comparator<sort.Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        //最后一个interval肯定还没被加到res中
        res.add(new Interval(start, end));
        return res;
    }
}




//分别sort start和end 更快
//有没有想过这种分别sort start和end的思路是怎么想出来的？
//这道题几个潜在条件 即使我们把start和end分开sort之后 对于每个interval end必然大于start
/*
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        // loop through
        List<Interval> res = new ArrayList<Interval>();
        //start of current interval
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || starts[i+1] > ends[i]) {
                res.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }
        return res;
    }
}
*/

