package hashtable;

import java.util.HashMap;
import java.util.Map;

//题意的理解 以及重复的处理 一个key出现多个不同的word(字典里) 放一个""进行

public class UniqueWordAbbreviation {
    Map<String, String> map;
    public UniqueWordAbbreviation(String[] dictionary) {
        this.map = new HashMap<>();
        for (String word : dictionary) {
            String key = getKey(word);
            if (map.containsKey(key)) {
                if (!map.get(key).equals(word)) {
                    map.put(key, "");
                }
            } else {
                map.put(key, word);
            }
        }
    }

    public boolean isUnique(String word) {
        String key = getKey(word);
        return !map.containsKey(key) || map.get(key).equals(word);
    }
    private String getKey(String s) {
        if (s.length() <= 2) {
            return s;
        } else {
            return "" + s.charAt(0) + (s.length()-2) + s.charAt(s.length() - 1);
        }
    }
}
