package graph;

//涉及到图论里的一些算法 一般都会比较难

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class FindEventualSafeStates {
//    public List<Integer> eventualSafeNodes(int[][] graph) {
//        List<Integer> res = new ArrayList<>();
//        int n = graph.length;
//        //color = 0 unvisited
//        int[] colors = new int[n];
//        for (int i = 0; i < n; i ++) {
//            if (dfs(graph, i, colors)) {
//                res.add(i);
//            }
//        }
//        return res;
//    }
//    private boolean dfs(int[][] graph, int cur, int[] colors) {
//        //DFS遇到放问过的点 状态是1表示已经check了没有环的存在
//        if (colors[cur] != 0) {
//            //color = 1 visited and safe after check all paths
//            //这一步check 包含了memorization在里面了顺便。。。挺秒的
//            return colors[cur] == 1;
//        }
//        //visited not checked safety yet
//        colors[cur] = 2;
//        for (int next : graph[cur]) {
//            if (!dfs(graph, next, colors)) {
//                return false;
//            }
//        }
//        colors[cur] = 1;
//        return true;
//    }
//}


public class FindEventualSafeStates {
    //White - unvisited
    //grey - visited but not safe
    //black - visited and safe
    enum Color {
        White,
        Grey,
        Black
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        //color = 0 unvisited
        Color[] colors = new Color[n];
        Arrays.fill(colors, Color.White);
        for (int i = 0; i < n; i ++) {
            if (dfs(graph, i, colors)) {
                res.add(i);
            }
        }
        return res;
    }
    private boolean dfs(int[][] graph, int cur, Color[] colors) {
        //if visited
        //if grey -> cycle    if black, the node is fully visited and safe return safe
        if (colors[cur] != Color.White) {
            return colors[cur] == Color.Black;
        }
        colors[cur] = Color.Grey;
        for (int next : graph[cur]) {
            if (!dfs(graph, next, colors)) {
                return false;
            }
        }
        colors[cur] = Color.Black;
        return true;
    }
}
