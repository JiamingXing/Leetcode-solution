package twopointers;

import java.util.List;

public class LongestWordThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        //这个问题应该分为两部分
        //第一是判断d中的每个word是不是s得sunsequence 用two pointer解决
        //第二是如果是 如何根据length以及lexicographical进行比较大小

        //这个地方怎么sort一个特定的数据结构 比如list 比如interval
        //用Collections 怎么精准改写Comparator 到底是a-b 还是b-a 一直很模糊
        //我下面写的这些也不确定对不对：
        /*
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() == b.length() ? b.compareTo(a) : a.length() - b.length();
            }
        });
        */
        String res = "";
        int max = Integer.MIN_VALUE;
        for (String word : d) {
            if (isSubsequence(s, word)) {
                if (word.length() > max) {
                    res = word;
                    max = word.length();
                } else if (word.length() == max) {
                    //res = lexicographical(res, word);
                    if (res.compareTo(word) > 0) {
                        res = word;
                    }
                }
            }
        }
        return res;
    }
    private boolean isSubsequence(String s, String word) {
        if (s.length() < word.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            char c = word.charAt(j);
            if (c == s.charAt(i)) {
                j ++;
            }
            if (j == word.length()) {
                break;
            }
            i ++;
        }
        return j == word.length();
    }
    //在这里我将比较相同长度的单词的大小 单独写成了一个method
    //也可以自己写个comparator 然后用Collections.sort(d, comp)进行sort
    private String lexicographical(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) < b.charAt(i)) {
                return a;
            } else if (a.charAt(i) == b.charAt(i)) {
                continue;
            } else {
                return b;
            }
        }
        return a;
    }
}
