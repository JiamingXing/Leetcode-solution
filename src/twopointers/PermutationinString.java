package twopointers;

//还是sliding window的思路 但是可能有点不一样 想起来会有一点小障碍
//因为不用纠结substring的位置或者长度 right放在哪里+都一样 只要维持left-right区间中没有hash[c] < 0的情况就可以

public class PermutationinString {
    public boolean checkInclusion(String s1, String s2) {
        int left = 0;
        int right = 0;
        int[] hash = new int[256];
        int count = s1.length();
        for (char c : s1.toCharArray()) {
            hash[c] ++;
        }
        while (right < s2.length()) {
            char c = s2.charAt(right);
            if (hash[c] > 0) {
                count --;
            }
            hash[c] --;
            if (count == 0) {
                return true;
            }
            right ++;
            while (hash[c] < 0) {
                char s = s2.charAt(left++);
                if (hash[s] >= 0) {
                    count ++;
                }
                hash[s] ++;
            }
        }
        return false;
    }
}


//别人的思路 本质上差不多 感觉自己的那个更快一点 不用每次都check 每一个char的个数是不是0
/*
public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1 == null || s1.length() == 0 || s2.length() == 0 || s1.length() > s2.length()) {
            return false;
        }
        int[] hash = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash[s1.charAt(i) - 'a'] ++;
            hash[s2.charAt(i) - 'a'] --;
        }
        if (isZero(hash)) {
            return true;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            hash[s2.charAt(i) - 'a'] --;
            hash[s2.charAt(i- s1.length()) - 'a'] ++;
            if (isZero(hash)) {
                return true;
            }
        }
        return false;
    }
    private boolean isZero(int[] hash) {
        for (int i = 0; i < hash.length ; i++) {
            if (hash[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
*/
