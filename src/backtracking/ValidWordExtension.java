package backtracking;

import java.util.ArrayList;
import java.util.List;

public class ValidWordExtension {
    public List<String> validWordExtension(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 3) {
            return res;
        }
        List<List<Integer>> intervals = getInterval(s);
        dfs(res, new StringBuilder(), s, intervals, 0, 0);
        return res;
    }
    private List<List<Integer>> getInterval(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        while (i < s.length() - 1) {
            if (s.charAt(i) == s.charAt(i+1)) {
                int count = 1;
                int start = i;
                while (i < s.length() - 1 && s.charAt(i+1) == s.charAt(i)) {
                    count ++;
                    i ++;
                }
                if (count >= 3) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(start);
                    temp.add(i);
                    res.add(temp);
                }
            }
            i ++;
        }
        return res;
    }
    private void dfs(List<String> res, StringBuilder sb, String s, List<List<Integer>> intervals, int pos, int prev) {
        if (pos == intervals.size()) {
            sb.append(s.substring(prev));
            res.add(sb.toString());
            return;
        }
        List<Integer> interval = intervals.get(pos);
        int start = interval.get(0);
        int end = interval.get(1);
        sb.append(s.substring(prev, start));
        int len = sb.length();
        sb.append(s.charAt(start));
        dfs(res, sb, s,intervals, pos+1, end+1);
        sb.setLength(len);
        sb.append(s.charAt(start)).append(s.charAt(start));
        dfs(res, sb, s, intervals, pos+1, end+1);
    }
}
