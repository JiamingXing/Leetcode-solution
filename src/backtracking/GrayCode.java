package backtracking;

import java.util.LinkedList;
import java.util.List;

/*
    LeetCode 89. Gray Code
    这道题的解法完全来自discussion
    The main idea : G(i) = i ^ (i/2)
 */

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) {
            result.add(i ^ i>>1);
        }
        return result;
    }
}
