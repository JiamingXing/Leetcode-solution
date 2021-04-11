package binarysearch;

//先不管能不能考虑到解决corner case的办法
//关键是拿到这样的题没有 能不能想到是用binary search来做？
//其实和divide two integers有点相似 或者说很多binary search的题都有共性
//如果我一个个相乘 那么我的时间复杂度O(n) 那么我们就想办法优化到O(logn)
//我们可以通过每次相乘因子相同的特点 把幂数不断折半，实现binary search

public class Pow {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                return 1.0/ (myPow(x, Integer.MAX_VALUE) * x);
            }
            n = -n;
            x = 1/x;
        }
        return (n % 2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }
}
