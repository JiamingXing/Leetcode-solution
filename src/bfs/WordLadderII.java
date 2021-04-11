package bfs;

import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        Map<String, Integer> depthMap = new HashMap<>();
        Map<String, List<String>> neighbors = new HashMap<>();
        bfs(beginWord, endWord, dict, depthMap, neighbors);
        dfs(res, path, depthMap, neighbors, beginWord, endWord);
        return res;
    }
    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, Integer> depthMap, Map<String, List<String>> neighbors) {
        for (String word : dict) {
            neighbors.put(word, new ArrayList<>());
        }
        int level = 1;
        depthMap.put(beginWord, level);
        Queue<String> Q = new LinkedList<>();
        Q.offer(beginWord);
        boolean found = false;
        while (!Q.isEmpty() && !found) {
            int size = Q.size();
            level ++;
            for (int i = 0; i < size; i++) {
                String cur = Q.poll();
                List<String> nextWord = getNextWord(cur, dict);
                for (String neighbor : nextWord) {
                    neighbors.get(cur).add(neighbor);
                    if (!depthMap.containsKey(neighbor)) {
                        depthMap.put(neighbor, level);
                        Q.offer(neighbor);
                    }
                    if (neighbor.equals(endWord)) {
                        found = true;
                    }
                }
            }
        }
    }
    private List<String> getNextWord(String cur, Set<String> dict) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {
            for (char c = 'a'; c < 'z'; c ++) {
                if (cur.charAt(i) == c) {
                    continue;
                }
                String changed = replace(cur, c, i);
                if (dict.contains(changed)) {
                    res.add(changed);
                }
            }
        }
        return res;
    }
    private String replace(String cur, char c, int pos) {
        char[] chs = cur.toCharArray();
        chs[pos] = c;
        return String.valueOf(chs);
    }
    private void dfs(List<List<String>> res, List<String> path, Map<String, Integer> depthMap,
                     Map<String, List<String>> neighbors, String cur, String end) {
        path.add(cur);
        for (String next : neighbors.get(cur)) {
            if (next.equals(end)) {
                res.add(new ArrayList<>(path));
            } else {
                if (depthMap.get(next) == depthMap.get(cur) + 1) {
                    dfs(res, path, depthMap, neighbors, next, end);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}

//这是自己二刷的时候AC的版本 思路略有不同但是代码风格明显上面的更好 把method都分开

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //BFS construct minimum-path graph
        int min = Integer.MAX_VALUE;
        int depth = 1;
        Queue<String> Q = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (String word : wordList) {
            dict.add(word);
        }
        Q.offer(beginWord);
        visited.add(beginWord);
        while (!Q.isEmpty()) {
            //endWord found
            if (min < Integer.MAX_VALUE) {
                break;
            }
            depth++;
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                String cur = Q.poll();
                if (graph.containsKey(cur)) {
                    continue;
                }
                graph.put(cur, new ArrayList<>());
                for (int j = 0; j < cur.length(); j++) {
                    char c = cur.charAt(j);
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k != c) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(cur.substring(0,j)).append(k).append(cur.substring(j+1));
                            String next = sb.toString();
                            if (dict.contains(next)) {
                                if (next.equals(endWord)) {
                                    min = depth;
                                }
                                if (!visited.contains(next)) {
                                    graph.get(cur).add(next);
                                    Q.offer(next);
                                }
                            }
                        }
                    }
                }
            }
            //add same level unique words to visited
            //this step makes sure graph constructed successfully
            size = Q.size();
            for (int i = 0; i < size; i++) {
                String cur = Q.poll();
                visited.add(cur);
                Q.offer(cur);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return res;
        }
        //dfs to generate all valid minimum paths
        dfs(res, temp, beginWord, endWord, graph);
        return res;
    }
    private void dfs(List<List<String>> res, List<String> temp, String cur, String endWord, Map<String, List<String>> graph) {
        temp.add(cur);
        if (cur.equals(endWord)) {
            res.add(new ArrayList<>(temp));
        }
        if (graph.containsKey(cur)) {
            for (String next : graph.get(cur)) {
                dfs(res, temp, next, endWord, graph);
            }
        }
        temp.remove(temp.size()-1);
    }
}