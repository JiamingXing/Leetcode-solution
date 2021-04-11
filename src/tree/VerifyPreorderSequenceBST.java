package tree;

import java.util.Stack;

//这道题的思路也不是自己想出来的
//个一个数组让你verify是不是某个bst的preorder
//先随便画一个bst 然后写出对应的preorder traversal数组 看看大概是什么样
//我们发现数组中元素的顺序应该是先沿着当前的left-subtree一直往下走
//直到我们找到left-most node之后开始往回找对应饿的right-subtree
//我们一旦发现 数组中的元素不再递减 说明我们进入了某个node的right-subtree
//我们找到对应的那个node 也就是不断pop stack直到stack里的元素要大于当前right node
//这个node的值就使我们当前的最小值 一直往下

public class VerifyPreorderSequenceBST {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int cur : preorder) {
            if (cur < low) {
                return false;
            }
            while (!stack.isEmpty() && cur > stack.peek()) {
                low = stack.pop();
            }
            stack.push(cur);
        }
        return true;
    }
}


//O(1) space solution
/*
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
*/



//自己想到的是用divide and conquer来做 会慢很多很多
/*
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length-1);
    }
    private boolean helper(int[] tree, int start, int end) {
        if (end - start < 2) {
            return true;
        }
        int cur = tree[start];
        int iter = start+1;
        while (iter <= end && tree[iter] < cur) {
            iter ++;
        }
        for (int i = iter; i <= end; i++) {
            if (tree[i] < cur) {
                return false;
            }
        }
        return helper(tree, start+1, iter-1) && helper(tree, iter, end);
    }
}
*/