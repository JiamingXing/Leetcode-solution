package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddBoldTaginString {
    private class Pair {
        int start;
        int end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public String addBoldTag(String s, String[] dict) {
        List<Pair> intervals = new ArrayList<>();
        for (String word : dict) {
            int start = 0;
            while (s.indexOf(word, start) != -1) {
                int temp = s.indexOf(word, start);
                intervals.add(new Pair(temp, temp+word.length()-1));
                start = temp + 1;
            }
        }
        List<Pair> mergedInterval = mergeInterval(intervals);
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for (Pair pair : mergedInterval) {
            int start = pair.start;
            int end = pair.end;
            sb.append(s.substring(prev, start));
            sb.append("<b>").append(s.substring(start, end+1)).append("</b>");
            prev = end + 1;
        }
        if (prev != s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }
    private List<Pair> mergeInterval(List<Pair> intervals) {
        List<Pair> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        int size = intervals.size();
        int[] start = new int[size];
        int[] end = new int[size];
        for (int i = 0; i < size; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int curStart = start[0];
        int curEnd = end[0];
        int i = 1;
        while (i < size) {
            if (start[i] > curEnd+1) {
                res.add(new Pair(curStart, curEnd));
                curStart = start[i];
                curEnd = end[i];
            } else {
                curEnd = end[i];
            }
            i ++;
        }
        res.add(new Pair(curStart, curEnd));
        return res;
    }
}




//用一个boolean数组记录 哪些是否bold
//这个别人的思路写的感觉太巧妙了。。。 先把问题转换为 我们用一个boolean array记录哪些char 是bold的自然就可以形成一个区间的概念
//然后对于如何得到这个boolean array 我们可以循环s的每一位 对每个word都判定一次 得到一个end 因为如果可以从i 找到一个匹配的word 证明
//i到i+word.length之间必然是bold的 我们用一个end巧妙记录 当前最长的结尾........  bold[i] = end > i 实在巧妙
/*
public class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }

        return result.toString();
    }
}
*/
