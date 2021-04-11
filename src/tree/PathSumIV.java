package tree;

import java.util.HashMap;
import java.util.Map;

//用一个hashmap 建树
//一开始经历过 直接通过数组进行解析 寻找一一对应的关系 然后遍历得到结果 其实差不多 但是没想明白
//如果能先清楚我解析完之后 可以找到对应的左右child 也就是完成用map建树的过程就好了

public class PathSumIV {
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int num : nums) {
            map.put(num/10, num%10);
        }
        dfs(nums[0]/10, 0);
        return sum;
    }
    private void dfs(int root, int cur) {
        int curSum = cur + map.get(root);
        int level = root/10;
        int pos = root%10;
        int left = (level+1)*10 + 2*pos - 1;
        int right = (level+1)*10 + 2*pos;
        if (!map.containsKey(left) && !map.containsKey(right)) {
            sum += curSum;
            return;
        }
        if (map.containsKey(left)) {
            dfs(left, curSum);
        }
        if (map.containsKey(right)) {
            dfs(right, curSum);
        }
    }
}
