package twopointers;

//之前在binary search类别做过一道关于array peak的题目 能否得到一些思路
//我觉得这道题最笨的思路... 就是先循环 如果A[i-1] < A[i] > A[i+1]从i这个位置向两边进行扩散 取一个mountain
//同时把指针直接跳到最right的点
//同时考虑 在array的两个边界的情况

//其实这就是正确的思路 当你AC之后 考虑下自己的代码有没有写的很拖沓 看看别人的solution 有没有可取之处..

//然后看到discuss第一名的做法之后 发现这可能不是面试官想要的结题思路
// https://leetcode.com/problems/longest-mountain-in-array/discuss/135593/C%2B%2BJavaPython-1-pass-and-O(1)-space
// 以前自己刷题可能做出来了就心满意足 感觉完成了一个任务.. 后续更多的去思考 开拓你的思维其实更重要 遇到难题锤炼自己的思考方式
// 就算最后自己没做出来 比较别人的思路 以及别人提供的思考的过程 可能对自己的提升更大

// 这道题别人的解法不仅解决问题 而且归纳了类似的题 都是用two-pass 一遍从前往后 一遍从后往前

// two pass

//public int longestMountain(int[] A) {
//    int N = A.length, res = 0;
//    int[] up = new int[N], down = new int[N];
//    for (int i = N - 2; i >= 0; --i) if (A[i] > A[i + 1]) down[i] = down[i + 1] + 1;
//    for (int i = 0; i < N; ++i) {
//        if (i > 0 && A[i] > A[i - 1]) up[i] = up[i - 1] + 1;
//        if (up[i] > 0 && down[i] > 0) res = Math.max(res, up[i] + down[i] + 1);
//    }
//    return res;
//}

// one pass
// 从two pass -> one pass 的思路需要一定的经验 并不容易分析出来 我们可以这么优化
//public int longestMountain(int[] A) {
//    int res = 0, up = 0, down = 0;
//    for (int i = 1; i < A.length; ++i) {
//        if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) up = down = 0;
//        if (A[i - 1] < A[i]) up++;
//        if (A[i - 1] > A[i]) down++;
//        if (up > 0 && down > 0 && up + down + 1 > res) res = up + down + 1;
//    }
//    return res;
//}

public class LongestMountaininArray {
    public static void main(String[] args) {
        int[] A = {1,3};
        LongestMountaininArray l = new LongestMountaininArray();
        System.out.println(l.longestMountain(A));
    }
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int i = 0, res = 0;
        while (i < A.length) {
            if (i > 0 && i < A.length-1 && A[i] > A[i-1] && A[i] > A[i+1]) {
                int left = i-1;
                int right = i+1;
                while (left > 0 && A[left] > A[left-1]) {
                    left --;
                }
                while (right < A.length-1 && A[right] > A[right+1]) {
                    right ++;
                }
                res = Math.max(res, right-left+1);
                i = right;
            } else {
                i ++;
            }
        }
        return res;
    }
}
