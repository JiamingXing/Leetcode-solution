package tree;

//这道题的思路其实不难的 很典型的分治
//我根据dep 找到左右子树的string 分别处理建树就好啦 但是可能代码写起来会比较棘手
//我们先处理一下S 把---转换成和level有关的分隔符 这样方便我们去split

public class RecoverBinaryTreeFromPreorderTraversal {
    public TreeNode recoverFromPreorder(String S) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < S.length()) {
            if (S.charAt(i) != '-') {
                sb.append(S.charAt(i));
                i ++;
            } else {
                int count = 0;
                while (i < S.length() && S.charAt(i) == '-') {
                    count ++;
                    i ++;
                }
                sb.append("(").append(count).append(")");
            }
        }
        return helper(sb.toString(), 1);
    }
    private TreeNode helper(String s, int dep) {
        if (s.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        String spliter = "(" + dep + ")";
        int pos = s.indexOf(spliter);
        if (pos == -1) {
            root.val = Integer.parseInt(s);
            return root;
        } else {
            root.val = Integer.parseInt(s.substring(0, pos));
        }
        int next = s.indexOf(spliter, pos+1);
        if (next == -1) {
            root.left = helper(s.substring(pos+spliter.length()), dep+1);
        } else {
            root.left = helper(s.substring(pos+spliter.length(), next), dep+1);
            root.right = helper(s.substring(next+spliter.length()), dep+1);
        }
        return root;
    }
}
