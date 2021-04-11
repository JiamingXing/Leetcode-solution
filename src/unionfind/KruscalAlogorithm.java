package unionfind;

//Kruscal algorithm 用Union-find来找weighted undirected graph中的MST
//最小spanning tree

//自己写一下如何表示一个weighted undirected graph?
//同理的 还是adjacent matrix grid的值就是weight  或者adjacent list 用一个map记录相邻点以及对应的weight

//初始化所有点指向自己 然后sort一下所有的edge 每次进最小的 union两头
//遍历一下所有的edge 其实不需要 你如果知道一共多少vertice 我们每次union的话就减1到0为止
public class KruscalAlogorithm {
}
