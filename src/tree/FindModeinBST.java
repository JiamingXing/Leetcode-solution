package tree;

import java.util.ArrayList;
import java.util.List;

//最简单直接的想法肯定是遍历一遍用一个map全部记录 但是我们可以用类似于摩斯投票算法来做

public class FindModeinBST {
    int count = 1;
    int max = 0;
    Integer prev = null;
    public int[] findMode(TreeNode root) {
        List<Integer> temp = new ArrayList<>();
        helper(root, temp);
        int[] res = new int[temp.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }
    private void helper(TreeNode root, List<Integer> temp) {
        if (root == null){
            return;
        }
        helper(root.left, temp);
        if (prev != null) {
            if (root.val == prev) {
                count ++;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            max = count;
            temp.clear();
            temp.add(root.val);
        } else if (count == max) {
            temp.add(root.val);
        }
        prev = root.val;
        helper(root.right, temp);
    }
}
