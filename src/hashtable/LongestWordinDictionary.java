package hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordinDictionary {
    public String longestWord(String[] dictionary) {
        Arrays.sort(dictionary);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String word : dictionary) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length()-1))) {
                if (word.length() > res.length()) {
                    res = word;
                }
                set.add(word);
            }
        }
        return res;
    }
}
