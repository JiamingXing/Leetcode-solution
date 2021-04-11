package backtracking;

/*
    LeetCode 291. Word Pattern II
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return helper(pattern, 0, str, 0, map, set);
    }
    private boolean helper(String pat, int i, String str, int j, Map<Character, String> map, Set<String> set) {
        if (i == pat.length() && j == str.length()) {
            return true;
        }
        if (i == pat.length() || j == str.length()) {
            return false;
        }
        char cur = pat.charAt(i);
        if (map.containsKey(cur)) {
            String matched = map.get(cur);
            /*
            可以用startsWith的方法
            if (!str.startsWith(matched, j)) {
                return false;
            }
            return helper(pat, i+1, str, j+matched.length(), map, set);
            */
            int len = matched.length();
            if (j+len <= str.length() && matched.equals(str.substring(j, j+len)) && helper(pat, i+1, str, j+len, map, set)) {
                return true;
            }
            return false;
        }
        for (int k = j+1; k <= str.length(); k++) {
            String temp = str.substring(j, k);
            if (set.contains(temp)) {
                continue;
            }
            map.put(cur, temp);
            set.add(temp);
            if (helper(pat, i+1, str, k, map, set)) {
                return true;
            }
            map.remove(cur);
            set.remove(temp);
        }
        return false;
    }
}


//二刷写的感觉写的不是很好 而且忘记check 不同字符map到相同string的情况了...
/*
class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return helper(pattern, 0, str, 0, map, set);
    }
    private boolean helper(String pattern, int p, String str, int s, Map<Character, String> map, Set<String> set) {
        if (p == pattern.length() && s == str.length()) {
            return true;
        }
        if (p == pattern.length() || s == str.length()) {
            return false;
        }
        char c = pattern.charAt(p);
        if (map.containsKey(c)) {
            String mapStr = map.get(c);
            if (s+mapStr.length() <= str.length() && str.substring(s, s+mapStr.length()).equals(mapStr)) {
                return helper(pattern, p+1, str, s+mapStr.length(), map, set);
            } else {
                return false;
            }
        } else {
            for (int i = s+1; i <= str.length(); i++) {
                String cur = str.substring(s, i);
                if (set.contains(cur)) {
                    continue;
                }
                map.put(c, cur);
                set.add(cur);
                if (helper(pattern, p+1, str, i, map, set)) {
                    return true;
                }
                map.remove(c);
                set.remove(cur);
            }
            return false;
        }
    }
}

 */