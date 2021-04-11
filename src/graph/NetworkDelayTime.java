package graph;

//用DFS会stack over flow...

import java.util.*;

//重新刷刷这道题 看以前写的代码 这个风格自己不喜欢

public class NetworkDelayTime {
    private class Pair {
        int next;
        int cost;
        public Pair(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
    int max = 0;
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] visited = new int[N+1];
        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int cost = time[2];
            if (!map.containsKey(source)) {
                map.put(source, new ArrayList<>());
            }
            map.get(source).add(new Pair(target, cost));
        }
        visited[K] = 1;
        dfs(map, K, 0, visited);
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (i == K || visited[i] > 0) {
                count ++;
            }
        }
        return count == N ? max : -1;
    }
    private void dfs(Map<Integer, List<Pair>> map, int cur, int path, int[] visited) {
        if (visited[cur] > 0 && path > visited[cur]) {
            return;
        }
        visited[cur] = path;
        if (!map.containsKey(cur)) {
            max = Math.max(max, path);
            return;
        }
        for (Pair pair : map.get(cur)) {
            dfs(map, pair.next, path+pair.cost, visited);
        }
    }
}

//二刷写的DFS 虽然很慢 但是感觉代码风格比以前的有进步的
/*
class Solution {
    class Edge {
        int dest;
        int weight;
        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1];
        int res = 0;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] time : times) {
            if (!graph.containsKey(time[0])) {
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new Edge(time[1], time[2]));
        }
        dfs(graph, dist, K);
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dist[i]);
        }
        return res;
    }
    private void dfs(Map<Integer, List<Edge>> graph, int[] dist, int cur) {
        if (!graph.containsKey(cur)) {
            return;
        }
        for (Edge e : graph.get(cur)) {
            if (dist[cur] + e.weight >= dist[e.dest]) {
                continue;
            }
            dist[e.dest] = dist[cur] + e.weight;
            dfs(graph, dist, e.dest);
        }
    }
}

 */


//Floyd Warshall
/*
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] M = new int[N + 1][N + 1];
        for(int[] row : M){
            Arrays.fill(row, 10000);
        }
        for(int[] t : times){
            int v1 = t[0];
            int v2 = t[1];
            int cost = t[2];
            M[v1][v2] = cost;
        }
        for(int m = 1; m <= N; m ++){
            for(int i = 1; i <= N; i ++){
                for(int j = 1; j <= N; j ++){
                    if(M[i][m] + M[m][j] < M[i][j]){
                        M[i][j] = M[i][m] + M[m][j];
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i ++){
            if(i == K){
                continue;
            }
            if(M[K][i] == 10000){
                return -1;
            }
            max = Math.max(max, M[K][i]);
        }
        return max == 10000 ? -1 : max;
    }
}
*/


//Dijkstra
/*
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        int[][] graph = new int[N + 1][N + 1];
        for(int[] row : graph){
            Arrays.fill(row, -1);
        }
        for(int[] t : times){
            graph[t[0]][t[1]] = t[2];
        }
        for(int i = 1; i <= N; i ++){
            if(graph[K][i] != -1){
                heap.offer(new int[]{K, i, graph[K][i]});
            }
        }
        int[] minCost = new int[N + 1];
        Arrays.fill(minCost, -1);
        while(!heap.isEmpty()){
            int[] cur = heap.poll();
            int u = cur[0];
            int v = cur[1];
            int cost = cur[2];
            if(minCost[v] == -1){
                minCost[v] = cost;
            }
            for(int i = 1; i <= N; i ++){
                if(graph[v][i] != -1 && minCost[i] == -1){
                    heap.offer(new int[]{K, i, cost + graph[v][i]});
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i ++){
            if(i == K){
                continue;
            }
            if(minCost[i] == -1){
                return -1;
            }
            max = Math.max(minCost[i], max);
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
*/



//Bellman Ford
/*
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[K] = 0;
        for(int i = 1; i <= N; i ++){
            if(i == K){
                continue;
            }
            for(int[] edge : times){
                int u = edge[0];
                int v = edge[1];
                int cost = edge[2];
                if(dis[u] != Integer.MAX_VALUE && dis[u] + cost < dis[v]){
                    dis[v] = dis[u] + cost;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i ++){
            if(i == K){
                continue;
            }
            max = Math.max(max, dis[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
*/