package hashtable;

import java.util.*;

public class KeyboardRow {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiop", s2 = "asdfghjkl", s3 = "zxcvbnm";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, 1);
        }
        for (char c : s2.toCharArray()) {
            map.put(c, 2);
        }
        for (char c : s3.toCharArray()) {
            map.put(c, 3);
        }
        List<String> res = new ArrayList<>();
        for (String word : words) {
            String lowWord = word.toLowerCase();
            if (!Character.isLetter(lowWord.charAt(0))) {
                continue;
            }
            int row = map.get(lowWord.charAt(0));
            int i = 1;
            while (i < lowWord.length()) {
                if (!Character.isLetter(lowWord.charAt(i)) || map.get(lowWord.charAt(i)) != row) {
                    break;
                }
                i++;
            }
            if (i == word.length()) {
                res.add(word);
            }
        }
        String[] result = new String[res.size()];
        int i = 0;
        while (i < res.size()) {
            result[i] = res.get(i);
            i ++;
        }
        return result;
    }
}


//看看别人写的多简洁...、
//LinkedList 可以直接toArray(new String[0]) ?  ArrayList可以吗？
/*
public class Solution {
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<strs.length; i++){
            for(char c: strs[i].toCharArray()){
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        List<String> res = new LinkedList<>();
        for(String w: words){
            if(w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0));
            for(char c: w.toUpperCase().toCharArray()){
                if(map.get(c)!=index){
                    index = -1; //don't need a boolean flag.
                    break;
                }
            }
            if(index!=-1) res.add(w);//if index != -1, this is a valid string
        }
        return res.toArray(new String[0]);
    }
}
*/