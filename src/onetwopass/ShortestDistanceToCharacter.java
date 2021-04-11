package onetwopass;

//最naive的做法就是每个点都往左往右找.. 时间复杂度应该是O(n^2)
//但是我们可以从左到右 更新c的位置 顺便更新之后所有char 左边最近的c的距离是多少
//同理从右到左... 然后综合一下得到结果...

//写的时候非常粗心... 第一遍直接对于res array就是取左右最小..根本没考虑如果有一个是0 那就要另一个...
//而且代码写的明显冗长 应该可以精简更好看一些

public class ShortestDistanceToCharacter {
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        int pos = -1;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == C) {
                pos = i;
            } else {
                if (pos == -1) {
                    res[i] = Integer.MAX_VALUE;
                } else {
                    res[i] = i - pos;
                }
            }
        }
        for (int i = n-1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                pos = i;
            } else {

            }
        }
        return res;
    }
}


//看看人家写的...
//初始的时候 pos = -n...绝了
/*
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        int pos = -n;
        for (int i = 0; i < n; ++i) {
            if (S.charAt(i) == C) pos = i;
            res[i] = i - pos;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (S.charAt(i) == C)  pos = i;
            res[i] = Math.min(res[i], Math.abs(i - pos));
        }
        return res;
    }

 */
