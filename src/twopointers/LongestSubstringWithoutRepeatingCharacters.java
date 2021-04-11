package twopointers;

//看到一个FB的follow up 假如字符串很大很大 而且以incoming stream的形式不断添加字符 如何更改让它不受内存限制

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int[] hash = new int[256];
        int res = Integer.MIN_VALUE;
        while (right < s.length()) {
            char cur = s.charAt(right);
            hash[cur] ++;
            right ++;
            if (hash[cur] == 2) {
                while (hash[cur] == 2) {
                    char temp = s.charAt(left);
                    hash[temp] --;
                    left ++;
                }
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
