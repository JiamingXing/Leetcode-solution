package string;

//用KMP算法 -> 学习下什么是KMP算法


//首先这道题要看出来 转换成 找到longest palindrome from left most character....
//然后就有自己的做法，但是不是最优
//最优的可能是recursive 和 KMP
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        int pos = -1;
        int i = s.length() - 1;
        char[] ch = s.toCharArray();
        char target = ch[0];
        while (i >= 0) {
            if (ch[i] != target) {
                i --;
                continue;
            } else {
                if (isPalindrome(ch, 0, i)) {
                    pos = i+1;
                    break;
                }
            }
            i --;
        }
        if (pos == s.length()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        while (pos < s.length()) {
            sb.insert(0, s.charAt(pos++));
        }
        return sb.toString();
    }
    private boolean isPalindrome(char[] ch, int start, int end) {
        while (start <= end) {
            if (ch[start] != ch[end]) {
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }
}


//KMP
/*
    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s){
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;
        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = table[i-1] + 1;
                index ++;
            }else{
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char
                    index ++ ;
                }

                table[i] = index;
            }

        }

        return table;
    }
*/


// recursive way
/*
    int j = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
            }
            if (j == s.length()) { return s; }
            String suffix = s.substring(j);
            return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
*/