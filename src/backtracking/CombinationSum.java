package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    LeetCode 39. Combination Sum
    A set of unique candidates numbers & the same candidates can be chosen unlimited times
 */

//What is the time complexity?

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] candidates, int target, int pos) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            temp.add(candidates[i]);
            helper(res, temp, candidates, target-candidates[i], pos);
            temp.remove(temp.size() - 1);
        }
    }
}


//先sort 优化一下 可以更快
/*
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int n = candidates.length;
        helper(res, temp, candidates, 0, target);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] candidates, int pos, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            if (candidates[i] > target) {
                return;
            }
            temp.add(candidates[i]);
            helper(res, temp, candidates, i, target-candidates[i]);
            temp.remove(temp.size()-1);
        }
    }
}
*/