package backtracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    LeetCode 47. Permutations II
    Given a collection of numbers that might contain duplicates, return all unique permutations
 */

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(res, temp, nums, visited);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i-1]) {
                continue;
            }
            temp.add(nums[i]);
            visited[i] = true;
            helper(res, temp, nums, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}
