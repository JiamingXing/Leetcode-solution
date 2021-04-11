package stack;

//看到这道题 能有什么思路？
//这种比较难的题 会碰到一种情况就是第一次做不会 看了discuss明白了怎么回事看懂了比人的思路
//然后交了过了 之后复习的时候拿到手里还是一点思路都没有
//下面的代码还是复制粘粘的代码 想一想为什么为什么 怎么想到这么做的 为什么想到用stack？

//复习二刷：对于随便拿一个例子你会发现 如果我们拿某一个作为基础往两边扩散 规则就是碰到比自身小的就必须停止了
//因为是被人开始的根基了...所以我们向左向右找到第一个比当前小的index就可以得到想要的位置
//对于最小值 因为找不到更小的 就拿最小值乘以整个array的长度
//问题转换一下 如何向左向右找第一个比他小的值？


//维持一个递增栈 思路是想着如果我栈内的高度是递增的 那么一直入栈
//直到我碰到高度比栈顶小的点 开始慢慢pop 直到栈顶高度小于当前高度
//为什么会用stack？ 因为stack先进后出的特点，小的先进的 我们要极可能的增加他的宽度 让结果又可比性
//所以我们要想到用stack 那么我们当维持这个规则进行出栈入栈的过程中 我们计算结果是不包括当前的点的
//我们只考虑当前位置之前所有可能的计算结果 最后把当前的位置入栈 所以我们的指针需要走到heights.length的地方

import java.util.Stack;

public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i <= heights.length; i++) {
            int current = (i == heights.length) ? -1 : heights[i];
            while (!stack.isEmpty() && current <= heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                result = Math.max(result, w * h);
            }
            stack.push(i);
        }
        return result;
    }
}
