package trie;

//找到出现在s1中的s2中的String 并按照s2的顺序排列 s1中剩下的word排序输出
//很巧妙的方法是能想到用trie 首先用trie的话 找word很方便 其次是要将s1剩下的word
//sort加入结果集中 用dfs寻找trie就是sorted的
//一种用空间换时间的方法 因为如果用set先找到重复元素，然后把剩下的加进来进行排序
//不如trie来得快

import java.util.ArrayList;
import java.util.List;

public class FindRepeatingWordSortRest {
    private class TrieNode {
        String word = "";
        TrieNode[] children = new TrieNode[26];
    }
    public String[] findRpeatingAndSort(String[] s1, String[] s2) {
        TrieNode root = new TrieNode();
        for (String s : s1) {
            addWord(s, root);
        }
        List<String> res = new ArrayList<>();
        for (String s : s2) {
            if (search(s, root)) {
                res.add(s);
            }
        }
        dfs(root, res);
        String[] result = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
    private void addWord(String s, TrieNode root) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.word = s;
    }
    private boolean search(String s, TrieNode root) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        if (cur.word.equals(s)) {
            cur.word = "";
            return true;
        }
        return false;
    }
    private void dfs(TrieNode root, List<String> res) {
        if (root == null) {
            return;
        }
        if (!root.word.isEmpty()) {
            res.add(root.word);
        }
        for (int i = 0; i < 26; i++) {
            dfs(root.children[i], res);
        }
    }
}
