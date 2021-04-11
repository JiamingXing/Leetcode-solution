package trie;

public class AddandSearchWord {

    private class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public AddandSearchWord() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(root, word, 0);
    }

    private boolean helper(TrieNode root, String word, int pos) {
        if (pos == word.length()) {
            return root.isWord;
        }
        char c = word.charAt(pos);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null && helper(root.children[i], word, pos+1)) {
                    return true;
                }
            }
            return false;
        } else {
            return root.children[c - 'a'] != null && helper(root.children[c - 'a'], word, pos+1);
        }
    }
}
