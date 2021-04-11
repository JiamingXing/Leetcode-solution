package backtracking;

/*
    LeetCode 216. Combination Sum III
    use number from 1-9 and each number can be used once
 */

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, n, k, 1);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int n, int k, int pos) {
        if (n == 0 && k== 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (n <= 0) {
            return;
        }
        if (k == 0) {
            return;
        }
        for (int i = pos; i <= 9 && i <= 9-k+1; i++) {
            temp.add(i);
            helper(res, temp, n-i, k-1, i+1);
            temp.remove(temp.size() - 1);
        }
    }
}
