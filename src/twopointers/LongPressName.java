package twopointers;

//写的太冗长了...


//看看人家写的版本 因为typed这个string是肯定全都要遍历一遍的
/*
public boolean isLongPressedName(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j)
        if (i < m && name.charAt(i) == typed.charAt(j))
        ++i;
        else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
        return false;
        return i == m;
        }
        */

public class LongPressName {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while (i < name.length() && j < typed.length()) {
            char n = name.charAt(i);
            char t = typed.charAt(j);
            if (n == t) {
                i ++;
                j ++;
            } else {
                if (j > 0 && t == typed.charAt(j-1)) {
                    j ++;
                } else {
                    return false;
                }
            }
        }
        if (i < name.length()) {
            return false;
        }
        if (j < typed.length()) {
            char c = typed.charAt(j-1);
            for (int k = j; k < typed.length(); k++) {
                if (typed.charAt(k) != c) {
                    return false;
                }
            }
        }
        return true;
    }
}
