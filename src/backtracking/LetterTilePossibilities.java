package backtracking;

import java.util.Arrays;

public class LetterTilePossibilities {
    int res = 0;
    public int numTilePossibilities(String tiles) {
        char[] ch = tiles.toCharArray();
        Arrays.sort(ch);
        boolean[] visited = new boolean[ch.length];
        helper(ch, visited, 0);
        return res-1;
    }
    private void helper(char[] ch, boolean[] visited, int count) {
        res ++;
        if (count == ch.length+1) {
            return;
        }
        for (int i = 0; i < ch.length; i++) {
            if (visited[i] || i > 0 && ch[i] == ch[i-1] && !visited[i-1]) {
                continue;
            }
            visited[i] = true;
            helper(ch, visited, count+1);
            visited[i] = false;
        }
    }
}
