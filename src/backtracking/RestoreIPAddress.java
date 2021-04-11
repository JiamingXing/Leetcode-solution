package backtracking;
import java.util.ArrayList;
import java.util.List;

/*
    LeetCode 93. Restore IP Address
    本题关键：IP address的规则是什么
    1. 必须分为4部分
    2. 每部分的第一位不能是0,如果长度大于1的话
    3. 每部分不能大于255
 */

public class RestoreIPAddress {
    public List<String> restoreIpAddress(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        helper(res, s, "", 0);
        return res;
    }
    private void helper(List<String> res, String s, String cur, int tag) {
        if (tag > 3) {
            if (s.isEmpty()) {
                res.add(cur);
            }
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if (Integer.parseInt(temp) <= 255) {
                if (temp.charAt(0) == '0' && temp.length() > 1) {
                    continue;
                }
                tag ++;
                String rest = s.substring(i, s.length());
                helper(res, rest, (tag < 4 ? cur + temp + "." : cur + temp), tag);
                tag --;
            }
        }
    }
}


//use stringBuilder to construct string
/*
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        helper(res, s, sb, 0);
        return res;
    }
    private void helper(List<String> res, String s, StringBuilder sb, int tag) {
        if (tag > 3) {
            if (s.equals("")) {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            String cur = s.substring(0, i);
            int len = sb.length();
            if (Integer.parseInt(cur) < 255) {
                if (s.charAt(0) == '0' && cur.length() > 1) {
                    continue;
                }
                tag ++;
                if (tag < 4) {
                    sb.append(cur).append(".");
                } else {
                    sb.append(cur);
                }
                String rest = s.substring(i, s.length());
                helper(res, rest, sb, tag);
                sb.setLength(len);
                tag --;
            }
        }
    }
}
*/
