package trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    private class TrieNode {
        String curWord = "";
        TrieNode[] children = new TrieNode[26];
    }
    private final static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) {
            return new ArrayList<String>();
        }
        List<String> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.curWord = word;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                helper(board, res, root, i, j);
            }
        }
        return res;
    }
    private void helper(char[][] board, List<String> res, TrieNode cur, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }
        char c = board[i][j];
        if (cur.children[c - 'a'] == null) {
            return;
        } else {
            cur = cur.children[c - 'a'];
            if (!cur.curWord.isEmpty()) {
                res.add(cur.curWord);
                //找到一个词之后remove当前的word 我们还要继续DFS因为不同的词有相同的prefix可以在一个path上的可能
                cur.curWord = "";
            }
        }
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            helper(board, res, cur, row, col);
        }
        board[i][j] = c;
    }
}
