package com.jimmy.twopointers;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        int left = 0;
        int right = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;
        int count = t.length();
        int[] hash = new int[256];
        for (char c : t.toCharArray()) {
            hash[c] ++;
        }
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (hash[cur] > 0) {
                count --;
            }
            hash[cur] --;
            right ++;
            if (count == 0) {
                while (count == 0) {
                    char temp = s.charAt(left);
                    if (right - left < min) {
                        min = right - left;
                        start = left;
                    }
                    if (hash[temp] == 0) {
                        count ++;
                    }
                    hash[temp] ++;
                    left ++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start+min);
    }
}
