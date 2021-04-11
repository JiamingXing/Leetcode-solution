package backtracking;
import java.util.ArrayList;
import java.util.List;
//import java.util.HashSet;
//import java.util.Set;



/*
  Leetcode 46. Permutations  tag: backtracking
  Summary solution : https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
 */

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, nums);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) {
                continue;
            }
            temp.add(nums[i]);
            helper(res, temp, nums);
            temp.remove(temp.size()-1);
        }
    }
}


//boolean[] visited 记录访问过的数值
/*
class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            helper(res, temp, nums, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}
*/


//因为List.contains的时间复杂度为n 想用set.add时间复杂度为1的方法来改进 但是set里的是排序的？
//这样的结果是不对的 比如[1,2,3] 出来的结果是6个[,1,2,3]
/*
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> temp = new HashSet<>();
        helper(res, temp, nums);
        return res;
    }
    private void helper(List<List<Integer>> res, Set<Integer> set, int[] nums) {
        if (set.size() == nums.length) {
            res.add(new ArrayList<>(set));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.add(nums[i])) {
                helper(res, set, nums);
                set.remove(nums[i]);
            }
        }
    }
}
*/