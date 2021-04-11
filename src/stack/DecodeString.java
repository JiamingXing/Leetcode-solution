package stack;

//这道题和Basic Calculator几乎一模一样 一个思路 考虑我们碰到括号的时候需要做什么？

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        Stack<String> stStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        int i = 0;
        String res = "";
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    count  = count * 10 + s.charAt(i) - '0';
                    i ++;
                }
                intStack.push(count);
            } else if (s.charAt(i) == '[') {
                stStack.push(res);
                res = "";
                i ++;
            } else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(stStack.pop());
                int repeatTime = intStack.pop();
                for (int j = 0; j < repeatTime; j++) {
                    sb.append(res);
                }
                res = sb.toString();
                i ++;
            } else {
                res += s.charAt(i);
                i ++;
            }
        }
        return res;
    }
}
