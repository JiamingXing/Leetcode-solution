package tree;

//感觉这道题很简单思路很直接 但是为什么写起来感觉有点坎坷。。。考虑一些东西感觉不熟练

public class LongestUnivaluePath {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        int connectMax = 0;
        int curLen = 0;
        if (root.left != null) {
            int left = helper(root.left);
            if (root.left.val == root.val) {
                curLen = Math.max(curLen, left + 1);
                connectMax += (left+1);
            }
        }
        if (root.right != null) {
            int right = helper(root.right);
            if (root.right.val == root.val) {
                curLen = Math.max(curLen, right+1);
                connectMax += (right+1);
            }
        }
        max = Math.max(max, connectMax);
        return curLen;
    }
}



//别人的代码 感觉很简洁 思路有一些区别 helper函数返回的是上层最大结果 我们在上层往下DFS的时候把当前的val传进去
/*
    int len = 0; // global variable
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        len = 0;
        getLen(root, root.val);
        return len;
    }

    private int getLen(TreeNode node, int val) {
        if (node == null) return 0;
        int left = getLen(node.left, node.val);
        int right = getLen(node.right, node.val);
        len = Math.max(len, left + right);
        if (val == node.val)  return Math.max(left, right) + 1;
        return 0;
    }
*/



/*
public class LongestUnivaluePath {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    private ResultType helper(TreeNode root) {
        int connectMax = 0;
        int curLen = 0;
        if (root.left != null) {
            ResultType left = helper(root.left);
            if (left.val == root.val) {
                curLen = Math.max(curLen, left.len + 1);
                connectMax += (left.len+1);
            }
        }
        if (root.right != null) {
            ResultType right = helper(root.right);
            if (right.val == root.val) {
                curLen = Math.max(curLen, right.len+1);
                connectMax += (right.len+1);
            }
        }
        max = Math.max(max, connectMax);
        return new ResultType(curLen, root.val);
    }
    private class ResultType {
        int len;
        int val;
        public ResultType(int len, int val) {
            this.len = len;
            this.val = val;
        }
    }
}
*/
