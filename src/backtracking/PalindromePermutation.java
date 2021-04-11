package backtracking;

/*
    267. Palindrome Partition II
 */

import java.util.ArrayList;
import java.util.List;

public class PalindromePermutation {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        List<Character> leftHalf = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        char odd = 'a';
        int[] hash = new int[256];
        boolean tag = false;
        for (char c : s.toCharArray()) {
            hash[c] ++;
        }
        for (int i = 0; i < 256; i++) {
            if (hash[i] % 2 == 1) {
                if (tag) {
                    return res;
                } else {
                    tag = true;
                    odd = (char) i;
                }
            }
            hash[i] = hash[i] / 2;
            for (int j = 1; j <= hash[i]; j++) {
                leftHalf.add((char)i);
            }
        }
        boolean[] visited = new boolean[leftHalf.size()];
        helper(temp, leftHalf, tag, odd, visited, new StringBuilder());
        mirror(res, temp, tag);
        return res;
    }
    private void helper(List<String> res, List<Character> left, boolean tag, char odd, boolean[] visited, StringBuilder sb) {
        if (sb.length() == left.size()) {
            if (tag) {
                sb.append(odd);
                res.add(sb.toString());
            } else {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < left.size(); i++) {
            if (visited[i] || (i > 0 && !visited[i-1] && left.get(i) == left.get(i-1))) {
                continue;
            }
            visited[i] = true;
            int len = sb.length();
            sb.append(left.get(i));
            helper(res, left, tag, odd, visited, sb);
            sb.setLength(len);
            visited[i] = false;
        }
    }
    private void mirror(List<String> res, List<String> temp, boolean tag) {
        for (String cur : temp) {
            StringBuilder sb = new StringBuilder();
            sb.append(cur);
            for (int i = tag ? cur.length() -2 : cur.length() -1; i >= 0; i--) {
                sb.append(cur.charAt(i));
            }
            res.add(sb.toString());
        }
    }
}
