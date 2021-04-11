package unionfind;

import java.util.Arrays;

//union find 如果两个点的root不同 就要进行union 同时component -1
//如果相同 表示属于同一个component 直接跳过
//有一个问题对于 这种无向图的union find (0,1) (1,0)表示同一条边 那么我们哪个union到哪个会有影响？

public class NumberofConnectedComponentsUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        int[] father = new int[n];
        Arrays.fill(father, -1);

        for (int[] edge : edges) {
            int x = find(father, edge[0]);
            int y = find(father, edge[1]);

            if (x == y) {
                continue;
            }
            father[x] = y;
            n --;
        }
        return n;
    }

    private int find(int[] num, int id) {
        if (num[id] == -1) {
            return id;
        }
        return find(num, num[id]);
    }
}
