package backtracking;

/*
    40. Ccombination Sum II
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, temp, candidates, target, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] candidates, int target, int pos) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (pos == candidates.length || target < 0) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            if (i > pos && candidates[i] == candidates[i-1]) {
                continue;
            }
            temp.add(candidates[i]);
            helper(res, temp, candidates, target-candidates[i], i+1);
            temp.remove(temp.size() - 1);
        }
    }
}


//二刷改良了一下 相比之前的 我们首先在训话你的时候加了一个提前break  因为当前元素大于target表示前面的sum溢出了
//而又因为我们的array是sort的 所以后面的只会更大 就不用进行下去了
//因为我们有提前break的机制在 递归函数的开头也不需要去check target<0的问题 并且也不用check pos==candidates.length
//因为进入循环直接就出来了...

/*
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
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
            //avoid duplicate in same level
            if (i > pos && candidates[i] == candidates[i-1]) {
                continue;
            }
            temp.add(candidates[i]);
            helper(res, temp, candidates, i+1, target-candidates[i]);
            temp.remove(temp.size()-1);
        }
    }
}
 */