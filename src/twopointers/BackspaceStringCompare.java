package twopointers;

import java.util.Stack;

//这里自己是用了stack 的写法
//如果不用额外的space 并且O(N)的时间怎么做 用two pointer

public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return false;
        }
        return buildString(S, new Stack<Character>()).equals(buildString(T, new Stack<Character>()));
    }
    private String buildString(String S, Stack<Character> stack) {
        for (char c : S.toCharArray()) {
            if (c == '#' && !stack.isEmpty()) {
                stack.pop();
            } else if (c != '#') {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}


//这个写法还是蛮难看懂的 但是写的很简洁...

/*
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--; j--;
            } else
                return i == -1 && j == -1;
        }
    }

    */
