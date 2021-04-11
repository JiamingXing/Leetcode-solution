package string;

//There is no limit on how many times a digit can be used

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (Character.isDigit(c)) {
                set.add(c - '0');
            }
        }
        //check minute part first
        String[] st = time.split(":");
        int[] timer = new int[]{Integer.parseInt(st[0]), Integer.parseInt(st[1])};
        next(timer);
        while (!valid(timer, set)) {
            next(timer);
        }
        String hour = timer[0] < 10 ? "0" + timer[0] : "" + timer[0];
        String min = timer[1] < 10 ? "0" + timer[1] : "" + timer[1];
        StringBuilder sb = new StringBuilder();
        sb.append(hour).append(":").append(min);
        return sb.toString();
    }
    private void next(int[] timer) {
        int hour = timer[0];
        int min = timer[1];
        min ++;
        if (min == 60) {
            min = 0;
            hour ++;
            if (hour == 24) {
                hour = 0;
            }
        }
        timer[0] = hour;
        timer[1] = min;
    }
    private boolean valid(int[] timer, Set<Integer> set) {
        return set.contains(timer[0] / 10) && set.contains(timer[0] % 10) &&
                set.contains(timer[1] / 10) && set.contains(timer[1] % 10);
    }
}

//别人的代码实现
/*
    public String nextClosestTime(String time) {
        String[] val = time.split(":");
        Set<Integer> set = new HashSet<>();
        int hour = add(set, val[0]);
        int minu = add(set, val[1]);

        int[] times = new int[] {hour, minu};
        nxt(times);

        while (!contains(times[0], times[1], set)) {
            nxt(times);
        }
        return valid(times[0]) + ":" + valid(times[1]);
    }

    public void nxt(int[] time) {
        int hour = time[0];
        int minu = time[1];
        minu ++;
        if (minu == 60) {
            hour ++;
            minu = 0;
            if (hour == 24) hour = 0;
        }
        time[0] = hour;
        time[1] = minu;
    }

    public int add(Set<Integer> set, String timeStr) {
        int time = Integer.parseInt(timeStr);
        set.add(time / 10);
        set.add(time % 10);
        return time;
    }

    public String valid(int time) {
        if (time >= 0 && time <= 9) return "0" + time;
        else return time + "";
    }

    public boolean contains(int hour, int minu, Set<Integer> set) {
        return set.contains(hour / 10) && set.contains(hour % 10) && set.contains(minu / 10) && set.contains(minu % 10);
    }
*/