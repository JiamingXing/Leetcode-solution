package hashtable;

import java.util.HashSet;
import java.util.Set;

//brute force的版本 自己也知道必然会TLE 那么在这个基础上看看能否进行优化呢？ 先分析brute force的时间空间复杂度
//在这种基础上 那部分可以优化 是给自己提供一条思路...
//最长的substring  他的substring也是duplicate 这点可以利用吗？

//实在想不到了...看discuss 关于这道题的解法关键词是suffix array以及binary search

public class LongestDuplicateSubstring {
    public String longestDupSubstring(String S) {
        int n = S.length();
        for (int i = 1; i < n; i++) {
            int len = n - i;
            Set<String> set = new HashSet<>();
            for (int j = 0; j <= i; j++) {
                String cur = S.substring(j, j+len);
                if (!set.add(cur)) {
                    return cur;
                }
            }
        }
        return "";
    }
}
