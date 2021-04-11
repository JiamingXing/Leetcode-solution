package stack;
//+ - * /

import java.util.Stack;
//知道用stack 处理 但是不能走上把 '+'，'-'和'*','/'分开的歪路
//因为举几个例子就能知道 有的时候* / 的结果要在碰到 + -的时候才能计算
//然后要注意当string到最后一位的时候也要处理 不管是什么

public class BasciCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = s.charAt(i);
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
