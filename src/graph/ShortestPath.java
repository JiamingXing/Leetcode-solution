package graph;

import java.util.*;

public class ShortestPath {
    class Wrapper {
        int node;
        int path;
        public Wrapper(int node, int path) {
            this.node = node;
            this.path = path;
        }
    }
    class WeightNode {
        int node;
        int weight;
        public WeightNode(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    //注意Dijkstra的精髓要去populate 所有的node  那么我们只有在pq poll出的时候 node必然是更新成功
    //
    public int shortestPath(int[][] edges, int n, int source, int destination) {
        Map<Integer, List<WeightNode>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            int weight = edge[2];
            graph.computeIfAbsent(n1, x->new ArrayList<>()).add(new WeightNode(n2, weight));
        }
        int[] dist = new int[n];
        boolean[] settled = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        PriorityQueue<Wrapper> pq = new PriorityQueue<>((a,b)->a.path-b.path);
        pq.add(new Wrapper(source,0));
        while (!pq.isEmpty()) {
            Wrapper w = pq.poll();
            if (settled[w.node]) {
                continue;
            }
            settled[w.node] = true;
//            if (w.path > dist[w.node]) {
//                continue;
//            }
            if (!graph.containsKey(w.node)) {
                continue;
            }
            for (WeightNode next : graph.get(w.node)) {
                if (!settled[next.node]) {
                    if (dist[w.node] + next.weight < dist[next.node]) {
                        dist[next.node] = dist[w.node] + next.weight;
                        pq.add(new Wrapper(next.node, dist[next.node]));
                    }
                }
//                if (w.path + next.weight < dist[next.node]) {
//                    dist[next.node] = w.path + next.weight;
//                    pq.add(new Wrapper(next.node, dist[next.node]));
//                }
            }
        }
        return dist[destination];
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,1},{0,2,5},{1,2,8},{2,3,2}};
        ShortestPath s = new ShortestPath();
        System.out.println(s.shortestPath(edges, 4,0, 3));
    }
}
