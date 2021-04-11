package string;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].startsWith(pre)) {
                continue;
            } else {
                while (!strs[i].startsWith(pre)) {
                    pre = pre.substring(0, pre.length() - 1);
                }
            }
        }
        return pre;
    }
}



/*
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs != null && strs.length > 0) {

            Arrays.sort(strs);

            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
        }

    }
}
*/