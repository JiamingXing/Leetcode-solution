package string;

import java.util.HashSet;
import java.util.Set;

public class ClosestTime {
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
