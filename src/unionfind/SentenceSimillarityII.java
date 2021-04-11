package unionfind;

//找simillar word的传递关系 比较典型的union find的题
//只要有关 可以看做两个点相连 都可以划分成一个component 归并到一个root

import java.util.*;

public class SentenceSimillarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }
        int[] parent = new int[2001];
        Map<String, Integer> map = new HashMap<>();
        Arrays.fill(parent, -1);
        int index = 1;
        for (String[] pair : pairs) {
            String l = pair[0];
            String r = pair[1];
            if (!map.containsKey(l)) {
                map.put(l, index++);
            }
            if (!map.containsKey(r)) {
                map.put(r, index++);
            }
            int pos1 = map.get(l);
            int pos2 = map.get(r);
            int root1 = find(parent, pos1);
            int root2 = find(parent, pos2);
            if (root1 != root2) {
                parent[root1] = root2;
            }
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i])) {
                return false;
            }
            int pos1 = map.get(words1[i]);
            int pos2 = map.get(words2[i]);
            if (find(parent, pos1) != find(parent, pos2)) {
                return false;
            }
        }
        return true;
    }
    private int find(int[] parent, int id) {
        if (parent[id] == -1) {
            return id;
        }
        parent[id] = find(parent, parent[id]);
        return parent[id];
    }
}


//DFS的做法 类似无向图 每个pair互相加入adj set
//两个词用dfs进行判断是否similar的时候我从某个词出发 用一个hashset作为visited记录访问过的点
/*
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Set<String>> pairInfo = new HashMap<>();
        for (String[] pair : pairs) {
            if (!pairInfo.containsKey(pair[0])) {
                pairInfo.put(pair[0], new HashSet<>());
            }
            if (!pairInfo.containsKey(pair[1])) {
                pairInfo.put(pair[1], new HashSet<>());
            }
            pairInfo.get(pair[0]).add(pair[1]);
            pairInfo.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (!pairInfo.containsKey(words1[i])) return false;
            if (!dfs(words1[i], words2[i], pairInfo, new HashSet<>())) return false;    //Search the graph.
        }
        return true;
    }

    public boolean dfs(String source, String target, Map<String, Set<String>> pairInfo, Set<String> visited) {
        if (pairInfo.get(source).contains(target)) return true;

        visited.add(source);
        for (String next : pairInfo.get(source)) {
            if (!visited.contains(next) && dfs(next, target, pairInfo, visited)) {
                return true;
            }
        }
        return false;
    }
}
*/
