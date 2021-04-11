package amazon;

//题干是 一堆城市 他们之间被铁路相连 然后有一部分铁路断掉了 每个铁路的修复费用不同 求最小cost连接所有城市
//这道题的输入应该是什么？ 我们需要每个城市与其他城市相连的情况以及对应的费用？
//  [[1,2,0], [1,3,500], [1,4,100], [2,3,0]]...  有一些铁路断了有一些还在...两个城市之间可能有多条铁路 选最小的？
//这是一个graph的题 表示一个graph我们可以用什么

//一道Minimum Spanning Tree的应用题
//Kruskal's algorithm:
//1. Sort all edges in non-decreasing order of their weight
//2. Pick the smallest Edge. Check if it forms a cycle with the spanning tree formed so far. If cycle not formed,include this edge
//3. Repear step 2 until there are V-1 edges in the spanning tree.
//This algorithm is a Greedy algorithm

//tiem complexity: sorting edges: O(ElogE) iterate through all edges and apply union-find: Union-find:O(logV)
//sum: O(ElogE) + EO(logV) ---  E = O(V^2)

public class MinimumCostPathRepairment {
    //public int minCost() {
        //sort all edges -> use min heap to store  +   get number of cities....
        //pick smallest one
        //if no cycle -> union find  add cost to result
        //until #city - 1   added....

    //}
}
