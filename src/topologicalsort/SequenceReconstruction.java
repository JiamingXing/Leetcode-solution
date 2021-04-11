package topologicalsort;

import java.util.*;

//如果我不是按照tag做题 可能这道题我就卡住了 想不到是拓扑搜索的题
//其实给你的那些sequence就是 给你的图 你把所有的进行拓扑排序之后

//还是写出了null pointer

//corner case org = {1} seqs = {1},{1},{1}

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                if (!graph.containsKey(seq.get(0)) && !inDegree.containsKey(seq.get(0))) {
                    graph.put(seq.get(0), new HashSet<>());
                    inDegree.put(seq.get(0), 0);
                }
                continue;
            }
            for (int i = 0; i < seq.size()-1; i++) {
                int n1 = seq.get(i);
                int n2 = seq.get(i+1);
                if (!graph.containsKey(n1)) {
                    graph.put(n1, new HashSet<>());
                }
                if (graph.get(n1).add(n2)) {
                    inDegree.put(n2, inDegree.getOrDefault(n2, 0)+1);
                }
                if (!inDegree.containsKey(n1)) {
                    inDegree.put(n1, 0);
                }
            }
        }
        //top sort
        Queue<Integer> Q = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                Q.add(key);
            }
        }
        int dep = 0;
        Map<Integer, Set<Integer>> topMap = new HashMap<>();
        while (!Q.isEmpty()) {
            int size = Q.size();
            topMap.put(dep, new HashSet<>());
            for (int i = 0; i < size; i++) {
                int cur = Q.poll();
                topMap.get(dep).add(cur);
                //null pointer
                if (!graph.containsKey(cur)) {
                    continue;
                }
                for (int next : graph.get(cur)) {
                    inDegree.put(next, inDegree.get(next)-1);
                    if (inDegree.get(next) == 0) {
                        Q.add(next);
                    }
                }
            }
            dep ++;
        }
        int i = 0, j = 0;
        while (i < org.length && j < dep) {
            if (topMap.get(j).contains(org[i])) {
                i ++;
            }
            j ++;
        }
        return i == org.length;
    }
}


//AC 这道题corner case 太恶心了
/*
public class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int[] seq: seqs) {
            if(seq.length == 1) {
                if(!map.containsKey(seq[0])) {
                    map.put(seq[0], new HashSet<>());
                    indegree.put(seq[0], 0);
                }
            } else {
                for(int i = 0; i < seq.length - 1; i++) {
                    if(!map.containsKey(seq[i])) {
                        map.put(seq[i], new HashSet<>());
                        indegree.put(seq[i], 0);
                    }

                    if(!map.containsKey(seq[i + 1])) {
                        map.put(seq[i + 1], new HashSet<>());
                        indegree.put(seq[i + 1], 0);
                    }

                    if(map.get(seq[i]).add(seq[i + 1])) {
                        indegree.put(seq[i + 1], indegree.get(seq[i + 1]) + 1);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
            if(entry.getValue() == 0) queue.offer(entry.getKey());
        }

        int index = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            if(size > 1) return false;
            int curr = queue.poll();
            if(index == org.length || curr != org[index++]) return false;
            for(int next: map.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0) queue.offer(next);
            }
        }
        return index == org.length && index == map.size();
    }
}
 */