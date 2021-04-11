package stack;

//说实话 看到之后有点不知道从何下手(算法层面)
//能够想到的 比如对于每一个重复的字符... 你只能留一个 留哪一个？从前往后考虑？因为要lexi最大
//如果重复字符的后一位是比当前的要大？那么是不是肯定不移除啊..因为我移除了 肯定比前面要大了？
//但是这种例子就比较尴尬 比如说dfjk a  前面几个都是可以删的...那么如何判断

//看solution 别人用的greedy+recursive的解法
//不是很理解这个思路

import java.util.Stack;

public class RemoveDuplicationLetters {
    //递增栈的思路好理解一些。。
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count[c-'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ///如果栈中有我们的这个元素了 那我们直接跳过
            //这里其实还没完全明白 为什么访问过的 一定就在哪个位置
            if (used[c-'a']) {
                count[c-'a']--;
                continue;
            }
            //如果栈顶元素比当前大 并且后面还有这些元素那我必然移除 为了保证我们的结果lexi order
            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek()-'a'] > 0) {
                used[stack.pop()-'a'] = false;
            }
            stack.push(c);
            used[c-'a'] = true;
            count[c-'a'] --;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
    //这个思路理解不能。。
//    public String removeDuplicateLetters(String s) {
//        int[] cnt = new int[26];
//        int pos = 0; // the position for the smallest s[i]
//        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) < s.charAt(pos)) pos = i;
//            if (--cnt[s.charAt(i) - 'a'] == 0) break;
//        }
//        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
//    }

}
