package tree;

import java.util.HashMap;
import java.util.Map;

//用了prefix sum的思路！！！ 思考一下 为什么在数组里能想到在树里你就想不到？
//递归函数到每个node 我们要找的是从之前任意一个节点到当前node的sum=target的case number
//加上所有孩子上的

public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, 0);
        return helper(root, map, 0, sum);
    }
    private int helper(TreeNode root, Map<Integer, Integer> map, int curSum, int target) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        int res = map.getOrDefault(curSum-target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0)+1);
        res = res + helper(root.left, map, curSum, target) + helper(root.right, map, curSum, target);
        return res;
    }
}


//一开始自己写的很慢的思路 相当于遍历两遍
/*
public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int left = pathSum(root.left, sum);
        int right = pathSum(root.right, sum);
        return left+right+helper(root, sum);
    }
    private int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, sum-root.val);
        int right = helper(root.right, sum-root.val);
        return left + right + (root.val == sum ? 1 : 0);
    }
}
*/