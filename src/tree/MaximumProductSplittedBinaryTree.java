package tree;

import java.util.HashSet;
import java.util.Set;

public class MaximumProductSplittedBinaryTree {
    public int maxProduct(TreeNode root) {
        Set<Long> set = new HashSet<>();
        long total = dfsSum(root, set);
        long res = 0;
        for (long key : set) {
            res = Math.max(res, key * (total-key));
        }
        //反正之前自己是不知道我对10^9+7取模是这么做的
        //res 是long 对一个int 取模之后是long  所以你还要cast一下
        //假如面试官问你你知道int多少位吗？最大值是多少....可能就直接跪了
        //因为最大值自己只会用Integer.MAX_VALUE然后对每种primitive type的基础就不上心
        return (int)(res % (int)(1e9 + 7));
    }
    private long dfsSum(TreeNode root, Set<Long> set) {
        if (root == null) {
            return 0;
        }
        long left = dfsSum(root.left, set);
        long right = dfsSum(root.right, set);
        //这里是long+long+int -> long? 为什么不需要cast int -> long
        //这部分基础很薄弱
        //long比int更精确 位数更多占用内存更多 所以int+long的结果是long
        //比如从int变成long 你不需要cast吧 因为你从一个  但是你从long变成int就要cast
        //因为你从一个更精确的东西变成更粗狂的东西
        set.add(left + right + root.val);
        return left + right + root.val;
    }
}
