package graph;

//deep copy的思路单一 先建立copy点和给的点的一一映射关系 然后处理一些neighbor的映射关系
//follow up:如果copy一张有向图？

import java.util.*;

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        List<UndirectedGraphNode> cache = new ArrayList<>();
        Q.offer(node);
        map.put(node , new UndirectedGraphNode(node.label));
        while (!Q.isEmpty()) {
            UndirectedGraphNode cur = Q.poll();
            cache.add(cur);
            for (UndirectedGraphNode temp : cur.neighbors) {
                if (!map.containsKey(temp)) {
                    map.put(temp, new UndirectedGraphNode(temp.label));
                    Q.offer(temp);
                }
            }
        }
        for (UndirectedGraphNode iterNode : cache) {
            for (UndirectedGraphNode temp : iterNode.neighbors) {
                map.get(iterNode).neighbors.add(map.get(temp));
            }
        }
        return map.get(node);
    }
}
