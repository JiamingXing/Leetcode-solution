package hashtable;

import java.util.HashMap;
import java.util.Map;

public class TransformString {
    public boolean canTransform(String s1, String s2) {
        if (s1 == null || s1 == null || s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (!map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), s2.charAt(i));
            } else {
                if (map.get(s1.charAt(i)) != s2.charAt(i)) {
                    return false;
                }
            }
        }
        return map.keySet().size() == 26 ? false : true;
        //if we want to make sure whether we have additional tranformation
        //we just need to iterate all the keys to judge whether the relative value is also the key of map
    }
}
