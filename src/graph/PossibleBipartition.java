package graph;

import java.util.*;

public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            int i = dislike[0];
            int j = dislike[1];
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
            graph.get(i).add(j);
            if (!graph.containsKey(j)) {
                graph.put(j, new ArrayList<>());
            }
            graph.get(j).add(i);
        }
        int[] colors = new int[N+1];
        Arrays.fill(colors, -1);
        for (int i = 1; i <= N; i++) {
            if (colors[i] == -1 && !dfs(graph, i, colors, 0)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, int cur, int[] colors, int prev) {
        if (colors[cur] != -1) {
            return colors[cur] == 1-prev;
        }
        colors[cur] = 1-prev;
        if (graph.containsKey(cur)) {
            for (int next : graph.get(cur)) {
                if (!dfs(graph, next, colors, colors[cur])) {
                    return false;
                }
            }
        }
        return true;
    }
}
