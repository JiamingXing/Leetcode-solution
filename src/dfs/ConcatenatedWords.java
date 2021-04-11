package dfs;

import java.util.*;

//如何保证至少2个word 粘贴而成？
//外面先一层保证至少两个 然后去做dfs
//这种方法过了 但是感觉有更快的解法？
//最快的应该是DP
//Trie + DFS也是一种解法 这个dfs的method可以返回一个integer 表示这个word可以由多少个word concatenate
//如果碰到一个node的word不是空还要继续往下

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInDict(String[] words) {
        List<String> res = new ArrayList<>();
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.length())) {
                map.put(word.length(), new HashSet<>());
            }
            map.get(word.length()).add(word);
        }
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                String temp = word.substring(0, i);
                int len = temp.length();
                if (map.containsKey(len) && map.get(len).contains(temp) && helper(word, i, map)) {
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }
    private boolean helper(String word, int pos, Map<Integer, Set<String>> dict) {
        if (pos == word.length()) {
            return true;
        }
        for (int i = pos+1; i <= word.length(); i++) {
            String temp = word.substring(pos, i);
            int len = temp.length();
            if (dict.containsKey(len) && dict.get(len).contains(temp) && helper(word, i, dict)) {
                return true;
            }
        }
        return false;
    }
}


//返回一个integer 代码更简洁一点
/*
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.length())) {
                map.put(word.length(), new HashSet<>());
            }
            map.get(word.length()).add(word);
        }
        for (String word : words) {
            if (helper(word, 0, map) > 1) {
                res.add(word);
            }
        }
        return res;
    }
    private int helper(String word, int pos, Map<Integer, Set<String>> dict) {
        if (pos == word.length()) {
            return 0;
        }
        for (int i = pos+1; i <= word.length(); i++) {
            String temp = word.substring(pos, i);
            int len = temp.length();
            if (dict.containsKey(len) && dict.get(len).contains(temp)) {
                int cur = helper(word, i, dict);
                if (cur >= 0) {
                    return cur + 1;
                }
            }
        }
        return -1;
    }
}
 */




//DP
/*
public class Solution {
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }

        return result;
    }

    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}
 */

