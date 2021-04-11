package string;

//S is custom sorted, get any permutation of T to be custom sorted
//in S no letter occurs more than once

//bucket sort

//这道题要求T满足S的顺序 但是S中没有的字符可以随便放
//想到用空间换时间的方法 用一个S长度的array 记录T中对应字符的个数
//同时用一个hashmap先处理S 就可以做到字符和index一一对应 O(1)获取
//只需要遍历S, T各一遍就可以得到答案，无关字符直接放在最前面就可以了

//我们也可以从T入手把T的字符放到bucket中，然后根据扫面S 根据S当前位置拿到T中数量append 最后再扫描一遍 append剩下的

//follow up:
//1. if T is too large
//2. if S is too large



import java.util.HashMap;
import java.util.Map;

public class CustomSortString {
    public String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), i);
        }
        int[] num = new int[S.length()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (!map.containsKey(c)) {
                sb.append(c);
            } else {
                num[map.get(c)] ++;
            }
        }
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                char c = S.charAt(i);
                for (int j = 0; j < num[i]; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
