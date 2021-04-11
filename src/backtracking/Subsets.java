package backtracking;
import java.util.ArrayList;
import java.util.List;

/*
    LeetCode 78. Subsets
    Given a set of distinct integers, return all possible subsets
    DFS
 */


public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, nums, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, int pos) {
        res.add(new ArrayList<>(temp));
        for (int i = pos; i < nums.length; i++) {
            temp.add(nums[i]);
            helper(res, temp, nums, i+1);
            temp.remove(temp.size() - 1);
        }
    }
}
