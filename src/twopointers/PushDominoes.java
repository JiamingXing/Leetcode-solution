package twopointers;

//不要对这种新颖的实际问题恐慌 仔细分析 就能写出来 因为思路本质上很简单 我们对于任何一个不是'L' 或 'R'的点
//只要找到左边第一个 和 右边第一个 就可以确定整个区间的值
//但是还有个老问题 就是这个populate 看起来是简单的分类讨论但是代码写的很冗长 看看能不能从写法上改进一下 更好看一点？
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] res = new char[dominoes.length()];
        char[] dom = dominoes.toCharArray();
        for (int i = 0; i < dominoes.length(); i++) {
            if (res[i] != '\u0000') {
                continue;
            }
            if (dom[i] == '.') {
                //find left and right...
                int left = findLeft(dom, i);
                int right = findRight(dom, i);
                //populate between left -> right
                populate(res, dom, left, right);
            } else {
                res[i] = dom[i];
            }
        }
        return String.valueOf(res);
    }
    private int findLeft(char[] dom, int ind) {
        int i = ind - 1;
        while (i >= 0 && dom[i] =='.') {
            i --;
        }
        return i;
    }
    private int findRight(char[] dom, int ind) {
        int i = ind + 1;
        while (i < dom.length && dom[i] == '.') {
            i ++;
        }
        return i;
    }
    private void populate(char[] res, char[] dom, int left, int right) {
        if (left == -1) {
            if (right != dom.length && dom[right] == 'L') {
                for (int i = 0; i < right; i++) {
                    res[i] = 'L';
                }
            } else {
                for (int i = 0; i < right; i++) {
                    res[i] = '.';
                }
            }
        } else if (right == dom.length) {
            if (dom[left] == 'R') {
                for (int i = left+1; i < right; i++) {
                    res[i] = 'R';
                }
            } else {
                for (int i = left+1; i < right; i++) {
                    res[i] = '.';
                }
            }
        } else if (dom[left] != dom[right]){
            if (dom[left] == 'L' && dom[right] =='R') {
                for (int i = left+1; i < right; i++) {
                    res[i] = '.';
                }
            } else {
                int i = left+1;
                int j = right-1;
                char leftC = dom[left];
                char rightC = dom[right];
                while (i < j) {
                    res[i++] = leftC;
                    res[j--] = rightC;
                }
                if (i == j) {
                    res[i] = '.';
                }
            }
        } else {
            for (int i = left+1; i < right; i++) {
                res[i] = dom[left];
            }
        }
    }
}



//别人的写法 无比简洁...
/*
    public String pushDominoes(String dominoes) {
        char[] a = dominoes.toCharArray();
        int L = -1, R = -1;//positions of last seen L and R
        for (int i = 0; i <= dominoes.length(); i++)
            if (i == a.length || a[i] == 'R') {
                if (R > L)//R..R, turn all to R
                    while (R < i)
                        a[R++] = 'R';
                R = i;
            } else if (a[i] == 'L')
                if (L > R || (R == -1 && L == -1))//L..L, turn all to L
                    while (++L < i)
                        a[L] = 'L';
                else { //R...L
                    L = i;
                    int lo = R + 1, hi = L - 1;
                    while (lo < hi) { //one in the middle stays '.'
                        a[lo++] = 'R';
                        a[hi--] = 'L';
                    }
                }
        return new String(a);
    }
    */
