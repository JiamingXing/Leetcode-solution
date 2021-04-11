package twopointers;

//第一反应想到的是sliding window的做法？ 但是这个sliding window的条件是真的挺难想的。。。。
//left->right  right-left == sum(L/4-#Char)

public class ReplaceSubstringForBalancedString {
    public int balancedString(String s) {
        int[] hash = new int[256];
        for (char c : s.toCharArray()) {
            hash[c] ++;
        }
        int l = s.length(), k = l/4, left = 0, right = 0;
        int res = Integer.MAX_VALUE;
        while (right < l) {
            char c = s.charAt(right++);
            hash[c] --;
            while (left < right && hash['Q'] <= k && hash['W'] <= k
             && hash['E'] <= k && hash['R'] <= k) {
                res = Math.min(res, right-left);
                hash[s.charAt(left++)] ++;
            }
        }
        return res;
    }
}
