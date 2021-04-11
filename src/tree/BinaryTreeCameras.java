package tree;

//首先能想到的思路 是连接点最多的那些点肯定优先作为camera 但是一想一个点最多连接的就三个node...如果我有一颗很full的tree
//假如用分治的想法 有一个想法是如果可以分析出叶子节点必然是1(也就是被camera到)的点 我们是不是可以从底向上进行分治
//有点像那道图的染色问题的解法思想？
//其实自己的思路approach很接近了 但是差点意思 首先是不能肯定从底向上一定是最优解 其次是染色不够明确

//叶子节点 必然不装camera 但是被monitored 我们return 0 表示没有camera但是monitor了
//如果子节点有camera 我们就返回1 表示monitor了 但是没有camera

public class BinaryTreeCameras {
    int res = 0;
    public int minCameraCover(TreeNode root) {
        return dfs(root) == 0 ? res+1 : res;
    }
    private int dfs(TreeNode root) {
        if (root == null) {
            return 2;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        //如果子节点还没被monitor 我们这里要放camera 并且return 1
        //return 1 it, we put monitor on this node
        if (left == 0 || right == 0) {
            res ++;
            return 1;
        }
        //如果子节点有camera了 那我们不需要放camera在当前节点 并且已经被monitor
        //return 2 if it is monitored and without camera
        if (left == 1 || right == 1) {
            return 2;
        }
        //如果子节点都是2表示没camera 并且已经被监控了 那我们当前node就表示没有camera 并且没被监控
        return 0;
    }
}
