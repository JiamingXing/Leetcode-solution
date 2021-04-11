package dfs;

/*
    394. Decode String
 */

import java.util.Stack;

//对于括号问题的处理我们往往可以想到用DFS以及Stack 相对的DFS一般和Stack绑定 Queue一般和BFS绑定
//真的得好好反思下 为什么复习的时候还是很难形成思路？

//理一下思路 首先碰到(的时候 外面必然是有数字，可能有字符 数字必然入栈 因为这个数字是和对应的)对应
//数字结束之后必然是(
//那么字符怎么办？ 但是我们要清楚一点就是我们碰到(的时候 我们要有一个新的string来记录括号里面的string
//碰到)的时候 我们把number pop出来表示频率 那么当前string=括号外的string+number*当前string
//这个应该就是我们需要的公式 有这个公式 思路应该就清晰了
//尝试下Basic Calculator能不能也用这种思路分析清楚公式

public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        int index = 0;
        String res = "";
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int num = 0;
                while (Character.isDigit(s.charAt(index))) {
                    num = num*10 + (s.charAt(index) - '0');
                    index ++;
                }
                intStack.push(num);
            } else if (s.charAt(index) == '[') {
                stringStack.push(res);
                res = "";
                index ++;
            } else if (s.charAt(index) == ']') {
                StringBuilder sb = new StringBuilder(stringStack.pop());
                int repeatTime = intStack.pop();
                for (int i = 0; i < repeatTime; i++) {
                    sb.append(res);
                }
                res = sb.toString();
                index ++;
            } else {
                res += s.charAt(index);
                index ++;
            }
        }
        return res;
    }
}
