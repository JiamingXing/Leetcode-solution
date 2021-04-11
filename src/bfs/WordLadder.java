package bfs;

import java.util.*;

//当时复习做的时候想过一个问题，需要几个visited来记录访问过的点，因为会想到我每次变化都是一条不同的路径
//那是不是每条路径都需要一个visited记录访问过的点 避免重复访问
//但是这里的潜在条件是 我用BFS进行搜索的过程中，如果我在当前节点可以变化成某个单词 那么我们不可能在后面的某一个变化中变成某个单词
//因为这道题要求找的是最短路径 既然是最短路径我们应该尽可能早的遍历所有可能

//BFS->最短路径

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        int res = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> Q = new LinkedList<>();
        Q.offer(beginWord);
        visited.add(beginWord);
        while (!Q.isEmpty()) {
            int size = Q.size();
            res ++;
            for (int i = 0; i < size; i++) {
                String cur = Q.poll();
                for (int j = 0; j < cur.length(); j++) {
                    char[] ch = cur.toCharArray();
                    for (char c = 'a'; c < 'z'; c ++) {
                        if (ch[j] == c) {
                            continue;
                        }
                        ch[j] = c;
                        String changed = String.valueOf(ch);
                        if (changed.equals(endWord)) {
                            return res;
                        }
                        if (visited.contains(changed)) {
                            continue;
                        }
                        if (wordList.contains(changed)) {
                            Q.offer(changed);
                            visited.add(changed);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
