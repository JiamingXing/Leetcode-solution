package dfs;

import java.util.*;

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
        Set<Integer> set = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            //这个set在每一层都new一下 但是这个reference每层都指的对应的set吗 还是reference每一层都是一个 会出问题？
            if (!set.add(nums[i])) {
                continue;
            }
            //跳过重复 同一层
            if (temp.size() == 0 || nums[i] >= temp.get(temp.size()-1)) {
                temp.add(nums[i]);
                helper(nums, i+1, res, temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        IncreasingSubsequences i = new IncreasingSubsequences();
        System.out.println(i.findSubsequences(nums).size());

        boolean[][] visited = new boolean[10][10];
        Arrays.fill(visited, true);
    }
}
