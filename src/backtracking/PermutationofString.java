package backtracking;

//return permutation of String in place with lexical order.

import java.util.ArrayList;
import java.util.List;

public class PermutationofString {
    public List<String> permutationSring(String s) {
        List<String> res = new ArrayList<>();
        int start = 0;
        int end = s.length() - 1;
        helper(s, start, end, res);
        return res;
    }
    private void helper(String s, int start, int end, List<String> res) {
        if (start == end) {
            res.add(s);
            return;
        }
        for (int i = start; i <= end; i++) {
            s = swap(s, start, i);
            helper(s, start+1, end, res);
            s = swap(s, start, i);
        }
    }
    private String swap(String s, int i, int j) {
        char[] ch = s.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }
}
