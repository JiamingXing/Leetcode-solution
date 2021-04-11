package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion {
    public String[] expand(String S) {
        //partition String
        List<String> temp = new ArrayList<>();
        String[] words = partition(S);
        helper(temp, words, 0, new StringBuilder());
        return temp.toArray(new String[0]);
    }
    private void helper(List<String> temp, String[] words, int pos, StringBuilder sb) {
        if (pos == words.length) {
            temp.add(sb.toString());
            return;
        }
        String cur = words[pos];
        if (cur.length() == 1) {
            sb.append(cur);
            helper(temp, words, pos+1, sb);
            sb.deleteCharAt(sb.length()-1);
        } else {
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                int len = sb.length();
                sb.append(c);
                helper(temp, words, pos+1, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    private String[] partition(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ',') {
                i ++;
            } else if (Character.isLetter(s.charAt(i))) {
                res.add("" + s.charAt(i++));
            } else if (s.charAt(i) == '{') {
                int pos = s.indexOf('}', i);
                String[] strArr = s.substring(i+1,pos).split(",");
                Arrays.sort(strArr);
                StringBuilder sb = new StringBuilder();
                for (String st : strArr) {
                    sb.append(st);
                }
                res.add(sb.toString());
                i = pos+1;
            }
        }
        return res.toArray(new String[0]);
    }
    public static void main(String[] args) {
//        String[] s = {"b", "a", "c"};
//        Arrays.sort(s);
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }
        BraceExpansion b = new BraceExpansion();
        String s = "{a,b}c{e,d}f";
        String[] st = b.expand(s);
        for (int i = 0; i < st.length; i++) {
            System.out.println(st[i]);
        }
    }
}
