package twopointers;

//two pointer

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; j < t.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i ++;
            }
            if (i == s.length()) {
                break;
            }
        }
        return i == s.length();
    }
}
