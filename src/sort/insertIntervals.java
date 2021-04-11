package sort;

import java.util.ArrayList;
import java.util.List;

public class insertIntervals {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && newInterval.start > intervals.get(i).end) {
            res.add(intervals.get(i));
            i ++;
        }
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start), Math.max(intervals.get(i).end, newInterval.end));
            i ++;
        }
        res.add(newInterval);
        while (i < intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    }
}
