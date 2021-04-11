package twopointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//第二遍做的时候写的
/*
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            map.put(c, i);
        }
        int start = 0;
        while (start != S.length()) {
            int right = map.get(S.charAt(start));
            for (int i = start + 1; i < right; i++) {
                char c = S.charAt(i);
                if (map.get(c) > right) {
                    right = map.get(c);
                }
            }
            res.add(right-start+1);
            start = right+1;
        }
        return res;
    }
}

*/

//别人的代码
/*
public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
        return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
        map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
        last = Math.max(last, map[S.charAt(i)-'a']);
        if(last == i){
        list.add(last - start + 1);
        start = last + 1;
        }
        }
        return list;
        }

 */

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int len = S.length();
        while (start != len) {
            char cur = S.charAt(start);
            int right = findLastIndex(S, start, cur);
            Map<Character, Integer> map = new HashMap<>();
            //注意如果更新的max大于当前 right要继续探索
            for (int i = start+1; i < right; i++) {
                char temp = S.charAt(i);
                if (map.containsKey(temp)) {
                    continue;
                } else {
                    map.put(temp, findLastIndex(S, i, temp));
                    //我们可以在循环中动态的更新n 所以这里的循环次数可能会变
                    right = Math.max(right, map.get(temp));
                }
            }
//            for (char c : map.keySet()) {
//                max = Math.max(max, map.get(c));
//            }
            res.add(right - start + 1);
            start = right + 1;
        }
        return res;
    }
    private int findLastIndex(String S, int start, char target) {
        int i = S.length()-1;
        int res = -1;
        while (i > start) {
            if (S.charAt(i) == target) {
                res = i;
                break;
            }
            i --;
        }
        return res == -1 ? start : res;
    }
}
