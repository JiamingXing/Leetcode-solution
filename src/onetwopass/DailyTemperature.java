package onetwopass;

import java.util.Stack;

//其实stack只要存index就可以了

public class DailyTemperature {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();
        //stack.push({0, T[0]});
        stack.push(new int[]{0, T[0]});
        for (int i = 1; i < T.length; i++) {
            while (!stack.isEmpty() && stack.peek()[1] < T[i]) {
                int index = stack.pop()[0];
                res[index] = i - index;
            }
            stack.push(new int[]{i, T[i]});
        }
        return res;
    }
}
