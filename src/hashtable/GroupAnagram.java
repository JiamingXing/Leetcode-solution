package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
    private String getKey(String s) {
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c-'a'] ++;
        }
        // 用StringBuilder 会快很多
        String res = "";
        for (int i = 0; i < 26; i++) {
            if (bucket[i] > 0) {
                res += bucket[i];
                res += (char)('a' + i);
            }
        }
        return res;
    }
}
