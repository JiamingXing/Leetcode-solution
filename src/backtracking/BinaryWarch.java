package backtracking;

import java.util.ArrayList;
import java.util.List;

public class BinaryWarch {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] hour = {1, 2, 4, 8};
        int[] min = {1, 2, 4, 8, 16, 32};
        for (int i = 0; i <= num && i < 4; i++) {
            int j = num - i;
            if (j >= 6) {
                continue;
            }
            List<Integer> hours = timeCombination(hour, i, 0);
            List<Integer> mins = timeCombination(min, j, 0);
            for (int h : hours) {
                for (int m : mins) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(h).append(":");
                    String temp = m < 10 ? "" + "0" + m : "" + m;
                    sb.append(temp);
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }
    private List<Integer> timeCombination(int[] hour, int n, int pos) {
        List<Integer> res = new ArrayList<>();
        if (n == 0) {
            res.add(0);
            return res;
        }
        for (int i = pos; i <= hour.length - n; i++) {
            int cur = hour[i];
            List<Integer> list = timeCombination(hour, n-1, i+1);
            for (int temp : list) {
                if (cur + temp >= 12) {
                    continue;
                }
                res.add(cur + temp);
            }
        }
        return res;
    }
}
