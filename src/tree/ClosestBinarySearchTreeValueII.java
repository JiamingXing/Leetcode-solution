package tree;

import java.util.*;

//会有很多问题

public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (Math.abs(o1 - target) - Math.abs(o2 - target)) > 0 ? 1 : -1;
            }
        });
        helper(root, pq, k, target);
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }
    private void helper(TreeNode root, PriorityQueue<Integer> pq, int k, double target) {
        if (root == null) {
            return;
        }
        pq.add(root.val);
        if (pq.size() > k) {
            pq.poll();
        }
        TreeNode next = root.val < target ? root.left : root.right;
        helper(next, pq, k, target);
    }
}

//根据BST inorder 是递增的特性
//那么我们LinkedList中的元素也是递增的
//LinkedList中是从小到大排列的 那么target不管是在这个区间的左边还是中间还是右边
//三种情况分析一下 我们通过判断当前root(list最右边) 和list最左边元素和target之间差的绝对值就可以分析出
//我们是否要移除头上的元素 如果你发现最左边的元素不再移除那么targte只可能在 区间左边或者中间
//这个时候我们return就可以了

/*
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        collect(root, target, k, res);
        return res;
    }

    public void collect(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;
        collect(root.left, target, k, res);

        if (res.size() == k) {
            //if size k, add curent and remove head if it's optimal, otherwise return
            if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst()))
                res.removeFirst();
            else return;
        }
        res.add(root.val);
        collect(root.right, target, k, res);
    }
}

 */




//别人AC的代码
/*
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
*/