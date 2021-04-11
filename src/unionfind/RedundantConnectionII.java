package unionfind;

//如果这道题直接用union find做 会出现的情况就是有一些case 你找到的是last edge 但是其实应该返回first edge 因为如果remove last edge
//剩下的图 可能不是rooted tree 就是会出现一个node有两个parent的情况

//如何用union find处理有向图

//这个问题分两个情况解决 因为有两种case都会导致这个tree structure invalid
//1. a node has two parents
//2. a circle exists

//first we check whether there is a node with two parents
//if so store them as candidate A and B, and set B to be invalid edge(cause B is the latter one in the array)
//then set this edge's child to be 0(remove this edge)
//then check whether there is a circle
//if the later edge exists or there is no candidate with two parents then we return this edge
//otherwise we just need to return our candidate

public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] root = new int[n+1], parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }
        int[] edge1 = null, edge2 = null, res = null;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parent[v] != 0) {
                //skip edge2 to do union-find
                //这一步是核心
                edge1 = new int[]{parent[v], v};
                edge2 = new int[]{u, v};
            } else {
                parent[v] = u;
                int root_u = find(root, u);
                int root_v = find(root, v);
                if (root_u != root_v) {
                    //union them
                    root[root_v] = root_u;
                } else {
                    //circle
                    res = edge;
                }
            }
        }
        if (edge1 != null && edge2 != null) {
            return res == null ? edge2 : edge1;
        }
        return res;
    }
    private int find(int[] root, int id) {
        if (root[id] == id) {
            return id;
        }
        root[id] = find(root, root[id]);
        return root[id];
    }
}




//public class RedundantConnectionII {
//    public int[] findRedundantDirectedConnection(int[][] edges) {
//        int[] edge1 = new int[]{-1, -1};
//        int[] edge2 = new int[]{-1, -1};
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int[] edge : edges) {
//            int child = edge[1];
//            int father = edge[0];
//            if (!map.containsKey(child)) {
//                map.put(child, father);
//            } else {
//                edge1 = new int[]{map.get(child), child};
//                edge2 = new int[]{father, child};
//                edge[1] = 0;
//            }
//        }
//        int[] parent = new int[edges.length+1];
//        Arrays.fill(parent, -1);
//        for (int[] edge : edges) {
//            if (edge[1] == 0) {
//                continue;
//            }
//            int u = edge[0];
//            int v = edge[1];
//            if (find(parent, u) == find(parent, v)) {
//                if (edge1[0] == -1) {
//                    return edge;
//                } else {
//                    return edge1;
//                }
//            }
//            parent[find(parent, v)] = find(parent, u);
//        }
//        return edge2;
//    }
//    private int find(int[] parent, int id) {
//        if (parent[id] == -1) {
//            return id;
//        }
//        parent[id] = find(parent, parent[id]);
//        return parent[id];
//    }
//}
