package backtracking;

/*
    254. Factor Combination
    核心在于 递归的因子循环从上一次的因子开始 这样可以规避重复
 */

import java.util.ArrayList;
import java.util.List;

public class FactorCombination {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, n, 2);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int start) {
        if (n == 1) {
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                helper(res, temp, n/i, i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
