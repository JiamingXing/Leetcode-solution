package hashtable;

//能够想到的思路 word的每一位有25种可能 把dictionary的每个词放到一个set中 然后尝试 时间复杂度O(25n) + O(m)
//用一个hashmap先处理dictionary 根据word length作为key
//然后根据搜索的word的length找到所有可能的词 逐个比较不同的char有几个 如果是一个就return true
//也可以把word 改变前和后的粘一起作为key 同时index和那一位的char作为value  这样保证得到的word是unique的
//也可以用Trie

//这道题可能会在 dictionary size很大 和 我们搜索这个word调用很多的地方做文章
//假如dictionary的size 是K 然后每个word的平均长度是n
//worst case O(Kn)

//如果我们用一个Trie来存我们的dictionary 然后遍历所有可能的改变进行搜索 时间复杂度应该是O（25n^2）

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicDictionary {
    Map<String, List<String>> map;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        this.map = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(word.substring(0,i)).append("#").append(word.substring(i+1,word.length()));
                String replace = sb.toString();
                if (!map.containsKey(replace)) {
                    map.put(replace, new ArrayList<>());
                }
                map.getOrDefault(replace, new ArrayList<>()).add(""+i+word.charAt(i));
            }
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(word.substring(0,i)).append("#").append(word.substring(i+1,word.length()));
            String replace = sb.toString();
            if (map.containsKey(replace)) {
                for (String val : map.get(replace)) {
                    if (!val.equals(""+i+word.charAt(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] dict = {"leetcode", "hello"};
        MagicDictionary m = new MagicDictionary();
        m.buildDict(dict);
        System.out.println(m.search("hhllo"));
    }
}

//这种做法也很好
/*
public class MagicDictionary {

    Map<Integer, List<String>> map = new HashMap<>();
    public MagicDictionary() {

    }


    public void buildDict(String[] dict) {
        for (String word : dict) {
            map.getOrDefault(word.length(), new ArrayList<>()).add(word);
        }
    }


    public boolean search(String word) {
        List<String> list = map.getOrDefault(word.length(), new ArrayList<>());
        for (String s : list) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (s.charAt(i) != word.charAt(i)) {
                    count ++;
                }
            }
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
*/



//时间复杂度 建Trie O(kn) 搜索 worst case O(25n^2)
//但是这样用Trie搜索是不是还不如不用 还不如用一个set解决？
/*
class MagicDictionary {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        public TrieNode() {}
    }
    TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }


    public void buildDict(String[] dict) {
        for (String s : dict) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }


    public boolean search(String word) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (arr[i] == c) {
                    continue;
                }
                char org = arr[i];
                arr[i] = c;
                if (helper(new String(arr), root)) {
                    return true;
                }
                arr[i] = org;
            }
        }
        return false;
    }
    public boolean helper(String s, TrieNode root) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }
}
*/


//这是Trie+DFS
/*
class MagicDictionary {


    TrieNode root;
    public MagicDictionary() {
        root = new TrieNode();
    }


    public void buildDict(String[] dict) {
        for (String word : dict) {
            TrieNode temp = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (temp.children[c-'a'] == null) temp.children[c-'a'] = new TrieNode();
                temp = temp.children[c-'a'];
            }
            temp.isWord = true;
        }
    }


    public boolean search(String word) {
        TrieNode temp = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            //only check replacement at this position
            for (int j = 0; j < 26; j++) {
                if ((char)(j+'a') == c || temp.children[j] == null) continue;

                if (helper(temp.children[j],word,i+1)) return true;
            }
            if(temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return false;
    }

    public boolean helper(TrieNode temp, String word, int index) {
        int len = word.length();
        for (int i = index; i < len; i++) {
            char c = word.charAt(i);
            if (temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return temp.isWord;
    }
}

class TrieNode {
    boolean isWord;
    TrieNode[] children;
    public TrieNode(){
        isWord = false;
        children = new TrieNode[26];
    }
}
*/
