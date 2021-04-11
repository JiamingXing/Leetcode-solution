package dp;

//之後上一道类似edit distance又比他简单的题 题目是给一个正数
//        可以做三种操作
//        (1) 减1
//        (2) 除2 (要确保可以整除才能使用此操作)
//        (3) 除3 (要确保可以整除才能使用此操作)
//        求最小几次操作就能把这数字转换成 1

import java.util.Arrays;

//这道题你能看出来用dp吧？
//要求最小的问题 并且你在n的时候的最小值只依赖于 n-1或者n/2或者n/3三种可能。。肯定是DP

public class MinimumStepMinimizeN {
    public int getMinSteps(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (dp[i-1] >= 0) {
                dp[i] = Math.max(dp[i], dp[i-1] + 1);
            }
            if (i % 2 == 0 && dp[i/2] >= 0) {
                dp[i] = Math.max(dp[i/2]+1, dp[i]);
            }
            if (i % 3 == 0 && dp[1/3] >= 0) {
                dp[i] = Math.max(dp[i/3]+1, dp[i]);
            }
        }
        return dp[n];
    }
}
