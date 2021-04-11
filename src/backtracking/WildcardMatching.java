package backtracking;

/*
    LeetCode 44. Wildcard Matching
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = p.charAt(i-1) == '*' && dp[0][i-1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i][j] || dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    //dp[i-1][j] means any sequence + s.charAt(i-1)
                    //dp[i][j-1] means empty sequence
                    dp[i][j] = dp[i][j] || dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}





//非DP的解法 但是最快
//This algorithm can also pass large set of test case
/*
class Solution {
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}
*/