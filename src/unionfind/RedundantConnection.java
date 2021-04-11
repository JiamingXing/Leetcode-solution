package unionfind;

//我觉得对于这种问题 肯定可以用DFS/BFS 解决 那么看看能不能快速想到BFS/DFS的思路
//如果用DFS来做要根据edge先建图 然后从某个点开始去搜索  但是题目要求return last occurence edge
//union find就是去遍历edge数组one by one 所以肯定是union find 最好

//一个graph 如果有N个vertices  并且他是tree 那么edge有V-1条 如果刚好有一条多余那么edge有N条

//然后再用union find来做 并且可以分析一下他们之间的利弊关系

//其实对于无向图 一个edge的连个点哪个在前哪个在后都是无所谓的

import java.util.Arrays;

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[2001];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            int s = edge[0];
            int l = edge[1];
            if (find(parent, s) == find(parent, l)) {
                return edge;
            }
            //union
            parent[find(parent, s)] = find(parent, l);
        }
        return new int[2];
    }
    private int find(int[] parent, int id) {
        if (parent[id] == -1) {
            return id;
        }
        parent[id] = find(parent, parent[id]);
        return parent[id];
    }
}
