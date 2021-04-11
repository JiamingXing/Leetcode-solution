package graph;

import java.util.Arrays;

//感觉题目不难，但是对于这种图的题目可能经验比较少 关键在于能不能想到需要你干什么
//比如这道题就是需要你用两种颜色进行涂色 不断将下一种颜色传到下一个相邻的node 如果颜色相同
//return false 但是很难想到，自己第一反应是想着去找环？？？

public class IsGraphBipartie {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1 && !dfs(graph, i, 0, colors)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int[][] graph, int node, int color, int[] colors) {
        if (colors[node] != -1) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int neighbor : graph[node]) {
            if (!dfs(graph, neighbor, 1-color, colors)) {
                return false;
            }
        }
        return true;
    }
}
