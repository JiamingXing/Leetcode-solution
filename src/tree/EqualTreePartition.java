package tree;

import java.util.HashMap;
import java.util.Map;

//注意处理[0,1,-1]的case

public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int totoal = dfs(root, map);
        if (totoal == 0) {
            return map.get(0) > 1;
        }
        return totoal % 2 == 0 && map.containsKey(totoal/2);
    }
    private int dfs(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, map);
        int right = dfs(root.right, map);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
