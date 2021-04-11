package twopointers;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int res = Integer.MIN_VALUE;
        int count = 0;
        int[] hash = new int[256];
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (hash[cur]++ == 0) {
                count ++;
            }
            right ++;
            if (count > 2) {
                while (count > 2) {
                    char temp = s.charAt(left);
                    if (hash[temp] == 1) {
                        count --;
                    }
                    hash[temp] --;
                    left ++;
                }
            }
            res = Math.max(res, right-left);
        }
        return res;
    }
}
