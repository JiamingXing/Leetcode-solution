package com.jimmy.dfs;

/*
    133. Clone Graph
 */

//clone deep copy这一类题 关键在于记录新老节点的映射关系
//给你一个节点 你要知道你new出来的节点是哪个 必须要有这个关系 比如random linked list 你要知道你新节点之间的random关系
//必须要通过原来的节点的random关系确定 原来节点的新节点的random 就是原来节点的random的新节点
//对于这个题 1.copy node 2.copy edges

import java.util.*;

//这里用两个Q会慢不少。。

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        Queue<UndirectedGraphNode> inQ = new LinkedList<>();
        Q.offer(node);
        //copy node
        while (!Q.isEmpty()) {
            UndirectedGraphNode cur = Q.poll();
            inQ.offer(cur);
            map.put(cur, new UndirectedGraphNode(cur.label));
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    Q.offer(neighbor);
                }
            }
        }
        //copy edges
        while (!inQ.isEmpty()) {
            UndirectedGraphNode cur = inQ.poll();
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}



//用ArrayList实现Queue 运行起来会快不少
/*
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        ArrayList<UndirectedGraphNode> Q = new ArrayList<>();
        Q.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        int start = 0;
        while (start < Q.size()) {
            UndirectedGraphNode cur = Q.get(start++);
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    Q.add(neighbor);
                }
            }
        }
        for (int i = 0; i < Q.size(); i++) {
            UndirectedGraphNode newNode = map.get(Q.get(i));
            for (UndirectedGraphNode neighbor : Q.get(i).neighbors) {
                newNode.neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}
*/
