package tree;

//这道题如果光按照I的思路顺下来有点难想 I的思路是根据BST的性质确定遍历方向找最接近的点
//其实这里有一些hint 根据BST 我们可以先找nextPrecdecessor和nextSuccessor两个函数 就像iterator一样
//那么思路就变成如何从BST 得到两个stack 分别是比当前点大的以及小的点
//我们想到的是 BST的inorder traversal 就是升序数组 反过来就是倒叙的
//然后分别从stack的peek出发得到K个点就可以了

//public class ClosestBSTValueII {
//    public List<Integer> closestKValues(TreeNode root, double target, int k) {
//        List<Integer> res = new ArrayList<>();
//
//    }
//}



/*
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<>();
        Stack<TreeNode> larger = new Stack<>();
        pushSmaller(root, target, smaller);
        pushLarger(root, target, larger);

        List<Integer> res = new ArrayList<>();
        TreeNode cur = null;
        while (res.size() < k) {
            if (smaller.isEmpty() || (!larger.isEmpty() && larger.peek().val - target < target - smaller.peek().val)) {
                cur = larger.pop();
                res.add(cur.val);
                pushLarger(cur.right, target, larger);
            } else {
                cur = smaller.pop();
                res.add(cur.val);
                pushSmaller(cur.left, target, smaller);
            }
        }
        return res;
    }

    private void pushSmaller(TreeNode node, double target,  Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val < target) {
                stack.push(node);
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }

    private void pushLarger(TreeNode node, double target, Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val >= target) {
                stack.push(node);
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
}
*/
