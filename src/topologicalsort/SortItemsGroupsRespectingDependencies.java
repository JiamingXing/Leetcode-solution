package topologicalsort;

import java.util.*;

//复杂拓扑搜索
//自己的思路是是否可以组内先sort
//然后根据组进行sort

public class SortItemsGroupsRespectingDependencies {
    Map<Integer, List<Integer>> groupGraph;
    Map<Integer, List<Integer>> itemGraph;
    int[] groupInDegree;
    int[] itemInDegree;
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        this.groupGraph = new HashMap<>();
        this.itemGraph = new HashMap<>();
        //单独的item 我们给他每个都分配一个group
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        groupInDegree = new int[m];
        itemInDegree = new int[n];
        //一开始这里我没有initialize
        for (int i = 0; i < n; i++) {
            itemGraph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            groupGraph.put(i, new ArrayList<>());
        }
        buildGroupGraph(group, beforeItems, n);
        buildItemGraph(beforeItems, n);
        //top sort item and group
        List<Integer> groupList = topSortUtil(groupGraph, groupInDegree, m);
        List<Integer> itemList = topSortUtil(itemGraph, itemInDegree, n);
        //check cycle
        //不管是group level  还是 item level 我们拓扑排序完之后 如果有环肯定不存在结果
        if (groupList.isEmpty() || itemList.isEmpty()) {
            return new int[0];
        }
        //这一步是自己没想到的。。。可以这么去merge
        //这一步拿来sort group内部的item关系。。。 真没想到
        //因为itemList里就是item level的拓扑排序关系
        Map<Integer, List<Integer>> groupItemMerge = new HashMap<>();
        for (int item : itemList) {
            groupItemMerge.computeIfAbsent(group[item], x->new ArrayList<>()).add(item);
        }
        int[] res = new int[n];
        int index = 0;
        //这里不是有m个grp吗？
        for (int grp : groupList) {
            //这么写报错了 没太想明白。。我前面itemList应该肯定有n个 而且能找到对应的m个group
            //看过corner case懂了 比如我初始有5个group m = 5
            //但是不是所有group 都有item的  比如只有group 0,2,3有item
            //然后-1的item会被放到从5开始的group中 这就是为什么我们要 这里要getDefault
            //这里list中的grpkey<=m个 但是有些key可能没有分配对应的值
            //List<Integer> temp = groupItemMerge.get(grp);
            List<Integer> temp = groupItemMerge.getOrDefault(grp, new ArrayList<>());
            for (int item : temp) {
                res[index++] = item;
            }
        }
        return res;
    }
    //top sort util to sort both item and group
    //also check cycle
    private List<Integer> topSortUtil(Map<Integer, List<Integer>> graph, int[] inDgree, int n) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDgree[i] == 0) {
                Q.add(i);
            }
        }
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            n --;
            res.add(cur);
            if (!graph.containsKey(cur)) {
                continue;
            }
            for (int next : graph.get(cur)) {
                inDgree[next] --;
                if (inDgree[next] == 0) {
                    Q.add(next);
                }
            }
        }
        return n == 0 ? res : new ArrayList<>();
    }
    private void buildGroupGraph(int[] group, List<List<Integer>> beforeItems, int n) {
        for (int i = 0; i < n; i++) {
            int curGroup = group[i];
            List<Integer> fromItems = beforeItems.get(i);
            for (int fromItem : fromItems) {
                int fromGroup = group[fromItem];
                if (fromGroup != curGroup) {
                    groupGraph.computeIfAbsent(fromGroup, x->new ArrayList<>()).add(curGroup);
                    groupInDegree[curGroup]++;
                }
            }
        }
    }
    private void buildItemGraph(List<List<Integer>> beforeItems, int n) {
        for (int i = 0; i < n; i++) {
            List<Integer> fromItems = beforeItems.get(i);
            for (int fromItem : fromItems) {
                itemGraph.computeIfAbsent(fromItem, x->new ArrayList<>()).add(i);
                itemInDegree[i] ++;
            }
        }
    }
}
