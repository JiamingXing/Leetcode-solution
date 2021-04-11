package dfs;

public class TernaryExpressionParser {
    public String parseTernary(String expression) {
        if (expression.isEmpty()) {
            return "";
        }
        return helper(expression, 0, expression.length()-1);
    }
    private String helper(String cur, int start, int end) {
        int i = start;
        while (i <= end) {
            if (cur.charAt(i) == 'T' || cur.charAt(i) == 'F') {
                int count = 0;
                int iter = i+2;
                while (iter <= end) {
                    if (cur.charAt(iter) == '?') {
                        count --;
                    } else if (cur.charAt(iter) == ':') {
                        count ++;
                    }
                    if (count == 1) {
                        return cur.charAt(i) == 'T' ? helper(cur, i+2, iter-1) : helper(cur, iter+1, end);
                    }
                    iter ++;
                }
            }
            i ++;
        }
        return cur.substring(start, end+1);
    }
}


//Stack with one pass solution
/*
public String parseTernary(String expression) {
    if (expression == null || expression.length() == 0) return "";
    Deque<Character> stack = new LinkedList<>();

    for (int i = expression.length() - 1; i >= 0; i--) {
        char c = expression.charAt(i);
        if (!stack.isEmpty() && stack.peek() == '?') {

            stack.pop(); //pop '?'
            char first = stack.pop();
            stack.pop(); //pop ':'
            char second = stack.pop();

            if (c == 'T') stack.push(first);
            else stack.push(second);
        } else {
            stack.push(c);
        }
    }

    return String.valueOf(stack.peek());
}
 */