package string;

import java.util.List;

//把所有时间转换成分钟分布在时间表上 然后考虑头尾的情况就行

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] time = new boolean[24*60];
        for (String s : timePoints) {
            String[] st = s.split(":");
            int temp = Integer.parseInt(st[0]) * 60 + Integer.parseInt(st[1]);
            if (time[temp]) {
                return 0;
            }
            time[temp] = true;
        }
        int prev = -1;
        int first = -1;
        int last = -1;
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (time[i]) {
                if (first == -1) {
                    first = i;
                }
                if (prev != -1) {
                    minimum = Math.min(minimum, i - prev);
                }
                prev = i;
            }
        }
        last = prev;
        return Math.min(minimum, 24 * 60 - last + first);
    }
}
