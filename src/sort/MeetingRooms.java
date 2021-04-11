package sort;

import java.util.Arrays;

public class MeetingRooms {
    public boolean canAttendMeetings(sort.Interval[] intervals) {
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 0; i < len-1; i++) {
            if (starts[i+1] < ends[i]) {
                return false;
            }
        }
        return true;
    }
}
