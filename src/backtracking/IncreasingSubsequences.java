package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, res, temp);
        return res;
    }
    private void helper(int[] nums, int pos, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() >= 2) {
            res.add(new ArrayList<>(temp));
        }
        //avoid duplicate in same level
        //这里要返回unique subsequences 又因为是sequence
        //所以同层避免重复就只能用HashSet 每一层recursion call都需要一个hashset
        Set<Integer> set = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                continue;
            }
            if (temp.size() == 0 || nums[i] >= temp.get(temp.size()-1)) {
                temp.add(nums[i]);
                helper(nums, i+1, res, temp);
                temp.remove(temp.size()-1);
            }
        }
    }
}
