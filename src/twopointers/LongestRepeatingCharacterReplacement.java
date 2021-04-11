package twopointers;

//本质是sliding window -> maximum subarray length with at most K different characters
//ABAB K = 3 把一个B先变成C再变成A AAAA output=4

import java.util.HashMap;
import java.util.Map;

//思路大体方向正确 但是卡在了这个max上。。。 其实不用更新？？现在都还有点没想明白

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, max = 0, res = 0;
        //int[] hash = new int[26] 代替hashmap
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char cur = s.charAt(right++);
            map.put(cur, map.getOrDefault(cur, 0)+1);
            if (map.get(cur) > max) {
                max = map.get(cur);
                //record cur?
            }
            //move left
            while (right-left-max > k) {
                //if max decrease...?
                char c = s.charAt(left++);

            }
            res = Math.max(res, right-left);
        }
        return res;
    }
}


/*
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

 */
