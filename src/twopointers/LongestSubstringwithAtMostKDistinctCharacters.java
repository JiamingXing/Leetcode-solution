package twopointers;

public class LongestSubstringwithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] hash = new int[256];
        int difNum = 0, left = 0, right = 0;
        int max = Integer.MIN_VALUE;
        while (right < s.length()) {
            hash[s.charAt(right)] ++;
            if (hash[s.charAt(right)] == 1) {
                difNum ++;
            }
            right ++;
            if (difNum > k) {
                while (difNum > k) {
                    if (hash[s.charAt(left)] == 1) {
                        difNum --;
                    }
                    hash[s.charAt(left)] --;
                    left ++;
                }
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
