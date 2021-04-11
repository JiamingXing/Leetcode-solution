package twopointers;

//应该要考察的版本就是这样写

public class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) {
                    return i;
                }
                if (i + j == haystack.length()) {
                    return -1;
                }
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
            }
        }
    }
}



//自己写的版本
//这道题的考察点并不是让你用subString()去做的
/*
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i+needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
*/
