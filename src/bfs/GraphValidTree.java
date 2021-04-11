package bfs;

//题目的核心很简单 就是如何判断一个无向图中是否有环 并且fully connected

import java.util.*;

//什么样的undirected graph is tree?
//acyclic and connected(only one component)

//这种方法比较慢 从某一个节点出发 把所有邻节点都加进来 同时移除邻节点的neighbor里当前node记录 如果碰到访问过的节点 就说明出现环
//最后要验证下是不是fully connected -> 如果不是是两棵树也要return false

//graph is tree的条件:从图中任意一点做DFS 不会出现环 并且可以搜索到途中所有的点(fully connected)
//做DFS的时候访问到之前访问过的点 就表示出现了环
//visited array在DFS之后 每个node对应的都是true 表示都访问过了 就表示fully connected
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        List<Set<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(0);
        visited[0] = true;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                int cur = Q.poll();
                for (int neighbor : adj.get(cur)) {
                    if (visited[neighbor]) {
                        return false;
                    }
                    adj.get(neighbor).remove(cur);
                    visited[neighbor] = true;
                    Q.offer(neighbor);
                }
            }
        }
        //check fully connected
        for (boolean temp : visited) {
            if (!temp) {
                return false;
            }
        }
        return true;
    }
}


//这种方法的本质相同 只不过上面的需要移除访问到的节点记录的当前节点 这里用0 1 2三个状态来区别 少一步操作
//染色DFS/BFS解 0表示unvisited 1表示visited 2表示fully visited
/*
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        }
        int m = edges.length;
        List<List<Integer>> adjacentList = new ArrayList<>();
        Queue<Integer> Q = new LinkedList<>();
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i ++) {
            adjacentList.get(edges[i][0]).add(edges[i][1]);
            adjacentList.get(edges[i][1]).add(edges[i][0]);
        }
        Q.add(edges[0][0]);
        visited[edges[0][0]] = 1;
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            for (int adj : adjacentList.get(cur)) {
                if (visited[adj] == 1) {
                    return false;
                } else if (visited[adj] == 0) {
                    Q.add(adj);
                    visited[adj] = 1;
                }
            }
            //end visit all adjacent node
            visited[cur] = 2;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
*/


//自己写的DFS版本
/*
class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            graph[n1][n2] = 1;
            graph[n2][n1] = 1;
        }
        Set<Integer> visited = new HashSet<>();
        if (!dfs(graph, 0, -1, n, visited) || visited.size() != n) {
            return false;
        }
        return true;
    }
    private boolean dfs(int[][] graph, int cur, int prev, int n, Set<Integer> visited) {
        if (!visited.add(cur)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (graph[cur][i] == 1 && i != prev) {
                if (!dfs(graph, i, cur, n, visited)) {
                    return false;
                }
            }
        }
        return true;
    }
}
 */


//自己写的union-find版本
/*
class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            if (!union(n1, n2, parent)) {
                return false;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                count ++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
    private boolean union(int n1, int n2, int[] parent) {
        int root_1 = find(parent, n1);
        int root_2 = find(parent, n2);
        if (root_1 == root_2) {
            return false;
        }
        parent[root_1] = root_2;
        return true;
    }
    private int find(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }
        parent[n] = find(parent, parent[n]);
        return parent[n];
    }
}

 */


//DFS 利用parent的思路 但是这个DFS还不是很懂 //搞明白了就是在DFS的过程中传一个parent的信息进去
//时间复杂度O(E+V)
/*
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}
*/



//union find的思路
/*
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;

            // union
            //这里有一个小问题就是我随便x->y 和 y->x不会有问题吗
            nums[x] = y;
        }

        return edges.length == n - 1;
    }

    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}
*/