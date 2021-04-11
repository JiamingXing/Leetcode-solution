package stack;

//首先这种碰到和括号有关的问题经常想到可能用stack解决
//复习的时候自己做出来了 关键在于考虑我们碰到括号的时候需要做什么？
//我们需要计算括号里的结果 所以我们可以先把括号之前计算的结果保存到stack里

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int i = 0;
        int res = 0;
        int sign = 1;
        Stack<Integer> intStack = new Stack<>();
        Stack<Integer> signStack = new Stack<>();
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i ++;
                }
                res += sign * num;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                sign = s.charAt(i) == '+' ? 1 : -1;
                i ++;
            } else if (s.charAt(i) == '(') {
                intStack.push(res);
                res = 0;
                signStack.push(sign);
                sign = 1;
                i ++;
            } else if (s.charAt(i) == ')'){
                res = intStack.pop() + signStack.pop() * res;
                i ++;
            } else {
                i ++;
            }
        }
        return res;
    }
}
