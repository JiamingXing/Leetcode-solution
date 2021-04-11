package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//之前一直在用map.containsValue 这是错的 这是O(n)的时间复杂度

public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            if ((map.containsKey(c) && !map.get(c).equals(words[i]))
                    || (!map.containsKey(c) && set.contains(words[i]))) {
                return false;
            }
            map.put(c, words[i]);
            set.add(words[i]);
        }
        return true;
    }
}
