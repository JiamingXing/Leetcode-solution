package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtree {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        helper(root, map);
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                res.add(map.get(key));
            }
        }
        return res;
    }
    private String helper(TreeNode root, Map<String, TreeNode> map) {
        if (root == null) {
            return "#";
        }
        String cur = root.val + "," + helper(root.left, map) + "," + helper(root.right, map);
        if (map.containsKey(cur)) {
            map.put(cur, root);
        } else {
            map.put(cur, null);
        }
        return cur;
    }
}
