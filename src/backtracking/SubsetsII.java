package backtracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    LeetCode 90. Subsets II
 */

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, nums, 0);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, int pos) {
        res.add(new ArrayList<>(temp));
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i-1]) {
                continue;
            }
            temp.add(nums[i]);
            helper(res, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
    }
}
