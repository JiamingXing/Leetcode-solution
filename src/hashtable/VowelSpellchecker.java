package hashtable;

import java.util.*;

public class VowelSpellchecker {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> set = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> cap = new HashMap<>();
        Map<String, String> devowel = new HashMap<>();
        String[] res = new String[queries.length];
        for (String word : wordlist) {
            String low = word.toLowerCase();
            //regex学一下
            String devowelWord = low.replaceAll("[aeiou]", "#");
            cap.putIfAbsent(low, word);
            devowel.putIfAbsent(devowelWord, word);
        }
        for (int i = 0; i < queries.length; i++) {
            String cur = queries[i];
            if (set.contains(cur)) {
                res[i] = cur;
                continue;
            }
            String low = cur.toLowerCase();
            String curDevowel = low.replaceAll("[aeiou]", "#");
            if (cap.containsKey(low)) {
                res[i] = cap.get(low);
            } else if (devowel.containsKey(curDevowel)) {
                res[i] = devowel.get(curDevowel);
            } else {
                res[i] = "";
            }
        }
        return res;
    }
}
