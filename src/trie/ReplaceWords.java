package trie;

import java.util.List;

//涉及到单词搜索的问题 我们应该尝试思考下 这道题会不会用trie去做
//然后不用trie的做法和用trie的做法时间复杂度差多少(trie是一个用空间换时间的数据结构)

//顺便思考下这里写class的时候 private的accessible scope的问题
//inner scope里的各种scope问题

//在写的时候 TrieNode中 String curWord 没有通过constructor定义的话 不会自动分配值

public class ReplaceWords {

    private class TrieNode {
        String curWord;
        TrieNode[] children = new TrieNode[26];
        TrieNode() {
            this.curWord = null;
        }
    }

    private class Trie {
        TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        private void addWord(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.curWord = word;
        }

        private String findPrefix(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    return word;
                } else {
                    if (cur.children[c - 'a'].curWord != null) {
                        return cur.children[c - 'a'].curWord;
                    }
                    cur = cur.children[c - 'a'];
                }
            }
            return word;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String word : dict) {
            trie.addWord(word);
        }
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String pre = trie.findPrefix(words[i]);
            sb.append(pre);
            sb.append(i == n-1 ? "" : " ");
        }
        return sb.toString();
    }
}




//这是不用trie的bucket sort的思路
//
/*
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        int max = 0;
        for (String root : dict) {
            max = Math.max(max, root.length());
        }
        List<String>[] bucket = new List[max+1];
        for (String root : dict) {
            int len = root.length();
            if (bucket[len] == null) {
                bucket[len] = new ArrayList<>();
            }
            bucket[len].add(root);
        }
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean found = false;
            for (int j = 1; j < bucket.length; j++) {
                if (bucket[j] != null) {
                    List<String> cur = bucket[j];
                    for (String root : cur) {
                        if (word.indexOf(root) == 0) {
                            sb.append(root).append(i == words.length - 1 ? "" : " ");
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                sb.append(word).append(i == words.length - 1 ? "" : " ");
            }
        }
        return sb.toString();
    }
}
*/