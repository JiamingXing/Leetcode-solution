package com.jimmy.twopointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        int len = s.length();
        int wordLen = words[0].length();
        int numWord = words.length;
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < len - numWord * wordLen + 1; i++) {
            Map<String, Integer> cur = new HashMap<>();
            int j = 0;
            while (j < numWord) {
                String temp = s.substring(i + j*wordLen, i + (j+1)*wordLen);
                if (countMap.containsKey(temp)) {
                    cur.put(temp, cur.getOrDefault(temp, 0) + 1);
                    if (cur.get(temp) > countMap.get(temp)) {
                        break;
                    }
                } else {
                    break;
                }
                j ++;
            }
            if (j == numWord) {
                res.add(i);
            }
        }
        return res;
    }
}
