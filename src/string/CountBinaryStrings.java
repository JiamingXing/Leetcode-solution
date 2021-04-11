package string;

//改进之后的写法：探索连续的0或1，分成两段计数，min(count, count)就是中间的结果
//然后把指针挪到第一个不同的字符开始

public class CountBinaryStrings {
    public int countBinarySubstrings(String s) {
        int start = 0;
        int res = 0;
        while (start < s.length()) {
            int count1 = 1;
            int count2 = 1;
            int index = start;
            while (index+1 < s.length() && s.charAt(index) == s.charAt(index+1)) {
                index ++;
                count1 ++;
            }
            if (index == s.length() - 1) {
                break;
            }
            start = ++index;
            while (index+1 < s.length() && s.charAt(index) == s.charAt(index+1)) {
                index ++;
                count2 ++;
            }
            res += Math.min(count1, count2);
            if (index == s.length() - 1) {
                break;
            }
        }
        return res;
    }
}


//别人的代码 写的比较简洁
/*
    public int countBinarySubstrings(String s) {
        int prevRunLength = 0, curRunLength = 1, res = 0;
        for (int i=1;i<s.length();i++) {
            if (s.charAt(i) == s.charAt(i-1)) curRunLength++;
            else {
                prevRunLength = curRunLength;
                curRunLength = 1;
            }
            if (prevRunLength >= curRunLength) res++;
        }
        return res;
    }
*/



/*
public class CountBinaryStrings {
    public int countBinarySubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i ++) {
            int count = 1;
            int j = i;
            while (j+1 < s.length() && s.charAt(j) == s.charAt(j+1)) {
                count ++;
                j ++;
            }
            j = j + 1;
            if (s.length() - j < count) {
                continue;
            }
            if (count == 1) {
                res ++;
                continue;
            }
            while (j + 1 < s.length() && s.charAt(j) == s.charAt(j+1)) {
                j ++;
                count --;
                if (count == 1) {
                    break;
                }
            }
            if (count == 1) {
                res ++;
            }
        }
        return res;
    }
}
*/