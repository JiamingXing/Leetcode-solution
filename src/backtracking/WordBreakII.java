package backtracking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    LeetCode 140. Word Break II
    记忆化搜索 + 递归
    有一点可能需要思考的是 我有一个word 但是我剩下的 不能再Dict里面找到break的可能 那么我返回的List是空的
 */

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        int max = 0;
        for (String cur : wordDict) {
            max = Math.max(cur.length(), max);
        }
        Map<String, List<String>> map = new HashMap<>();
        return helper(s, wordDict, map, max);
    }
    private List<String> helper(String s, List<String> wordDict, Map<String, List<String>> map, int max) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) {
            res.add("");
            return res;
        }
        for (int i = 0; i <= s.length() && i <= max ; i++) {
            String former = s.substring(0, i);
            if (wordDict.contains(former)) {
                List<String> list = helper(s.substring(i), wordDict, map, max);
                for (String temp : list) {
                    res.add(former + (temp.isEmpty()? "" : " ") + temp);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}

