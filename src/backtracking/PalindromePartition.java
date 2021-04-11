package backtracking;

/*
    LeetCode 131. Palindrome Partition
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        helper(res, temp, s);
        return res;
    }
    private void helper(List<List<String>> res, List<String> temp, String s) {
        if (s.isEmpty()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String cur = s.substring(0, i);
            if (isPalindrome(cur)) {
                temp.add(cur);
                String rest = s.substring(i, s.length());
                helper(res, temp, rest);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i ++;
            j --;
        }
        return true;
    }
}



//加了memorization...类似Word Break II的写法 反而更慢了...为什么？
class Solution {
    public List<List<String>> partition(String s) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = helper(s, map);
        return splitWords(list);
    }
    private List<String> helper(String s, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) {
            res.add("");
        }
        for (int i = 1; i <= s.length(); i++) {
            String first = s.substring(0, i);
            if (isPalindrome(first)) {
                List<String> next = helper(s.substring(i), map);
                for (String str : next) {
                    res.add(first + (str.isEmpty() ? str : " " + str));
                }
            }
        }
        map.put(s, res);
        return res;
    }
    private List<List<String>> splitWords(List<String> list) {
        List<List<String>> res = new ArrayList<>();
        for (String s : list) {
            List<String> temp = new ArrayList<>();
            for (String partition : s.split(" ")) {
                temp.add(partition);
            }
            res.add(temp);
        }
        return res;
    }
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i ++;
            j --;
        }
        return true;
    }
}
