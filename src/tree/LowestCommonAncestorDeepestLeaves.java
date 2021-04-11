package tree;

public class LowestCommonAncestorDeepestLeaves {
    class wrapper {
        TreeNode anc;
        int dep;
        public wrapper(TreeNode anc, int dep) {
            this.anc = anc;
            this.dep = dep;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root, 0).anc;
    }
    //找到每个node 应该return的ancestor的递归公式 适用于所有的node
    private wrapper dfs(TreeNode root, int dep) {
        if (root == null) {
            return new wrapper(null, dep);
        }
        wrapper left = dfs(root.left, dep+1);
        wrapper right = dfs(root.right, dep+1);
        if (left.anc == null && right.anc == null) {
            return new wrapper(root, dep);
        }
        if (left.anc == null) {
            return right;
        }
        if (right.anc == null) {
            return left;
        }
        if (left.dep == right.dep) {
            return new wrapper(root, left.dep);
        }
        return left.dep > right.dep ? left : right;
    }
}
