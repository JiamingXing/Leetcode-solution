package backtracking;

import java.util.*;

//思路没问题 但是TLE了想想哪里可以优化？ 但是我觉得时间复杂度也不是很高啊。。 最多就10^10?

public class VerbalArithmeticPuzzle {
    public boolean isSolvable(String[] words, String result) {
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                set.add(c);
            }
        }
        for (char c : result.toCharArray()) {
            set.add(c);
        }
        if (set.size() > 10) {
            return false;
        }
        boolean[] visited = new boolean[10];
        List<Character> list = new ArrayList<>(set);
        Map<Character, Integer> map = new HashMap<>();
        return helper(list, map, visited, words, result, 0);
    }
    private boolean helper(List<Character> list, Map<Character, Integer> map, boolean[] visited, String[] words, String result, int pos) {
        if (pos == list.size()) {
            return isValid(map, words, result);
        }
        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }
            map.put(list.get(pos), i);
            visited[i] = true;
            if (helper(list, map, visited, words, result, pos+1)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
    private boolean isValid(Map<Character, Integer> map, String[] words, String result) {
        for (String word : words) {
            char c = word.charAt(0);
            if (map.containsKey(c) && map.get(c) == 0) {
                return false;
            }
        }
        if (map.containsKey(result.charAt(0)) && map.get(result.charAt(0)) == 0) {
            return false;
        }
        long res = 0;
        for (String word : words) {
            res += calculate(word, map);
        }
        return res == calculate(result, map);
    }
    private long calculate(String word, Map<Character, Integer> map) {
        long res = 0;
        for (int i = 0; i < word.length(); i++) {
            res = res * 10 + map.get(word.charAt(i));
        }
        return res;
    }

    public static void main(String[] args) {
        VerbalArithmeticPuzzle v = new VerbalArithmeticPuzzle();
        String[] words = {"SEND", "MORE"};
        String result = "MONEY";
        System.out.println(v.isSolvable(words, result));
    }
}
