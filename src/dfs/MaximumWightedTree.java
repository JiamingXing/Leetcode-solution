package dfs;

import java.util.List;

//如果这不是一个rooted的tree 而是一个DAG(无环有向图) 我们就可以遍历所有的node 进行DFS + 一个Memorization记录每个node 做DFS的最大路径

public class MaximumWightedTree {
    int max;
    private class NTreeNode {
        int val;
        List<NTreeNode> children;
        List<Integer> weight;
        public NTreeNode(int val) {
            this.val = val;
        }
    }
    public int maxWeight(NTreeNode root) {
        dfs(root, 0);
        return max;
    }
    private void dfs(NTreeNode root, int sum) {
        if (root.children == null) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i), sum+root.weight.get(i));
        }
    }
}
